package com.studio.ir.cikarangsmartangkotbeta;

import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.Log;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PolylineOptions;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Maps_Path_Ciklip extends FragmentActivity {

	private static final LatLng CIKARANG = new LatLng(-6.256163, 107.144389);
    private static final LatLng PSGOMB = new LatLng(-6.284871, 107.153095);
	private static final LatLng CIBARUSAH= new LatLng(-6.323774, 107.127518);
	private static final LatLng LIPPO = new LatLng(-6.332475, 107.137818);


	GoogleMap googleMap;
	final String TAG = "PathGoogleMapActivity";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_path_google_map);
		SupportMapFragment fm = (SupportMapFragment) getSupportFragmentManager()
				.findFragmentById(R.id.map);
		googleMap = fm.getMap();

		MarkerOptions options = new MarkerOptions();
		options.position(CIKARANG);
        options.position(PSGOMB);
		options.position(CIBARUSAH);
		options.position(LIPPO);

		googleMap.addMarker(options);
		String url = getMapsApiDirectionsUrl();
		ReadTask downloadTask = new ReadTask();
		downloadTask.execute(url);

		googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(PSGOMB,
				13));
		addMarkers();


	}

	private String getMapsApiDirectionsUrl() {
		String waypoints = "waypoints=optimize:true|"

                + CIKARANG.latitude + "," + CIKARANG.longitude
                + "|" + "|" + "|" + PSGOMB.latitude + ","
                + PSGOMB.longitude + "|" + "|" + CIBARUSAH.latitude + ","
                + CIBARUSAH.longitude + "|"
                + LIPPO.latitude + "," + LIPPO.longitude;

		String sensor = "sensor=false";
		String params = waypoints + "&" + sensor;
		String output = "json";
		String url = "https://maps.googleapis.com/maps/api/directions/"
				+ output + "?" + params;
		return url;
	}

	private void addMarkers() {
		if (googleMap != null) {
            googleMap.addMarker(new MarkerOptions().position(CIKARANG)
                    .title("Cikarang"));
            googleMap.addMarker(new MarkerOptions().position(CIKARANG)
                    .snippet("Lokasi Terdekat: 1. Polsek, 2.Bank BRI, 3.Pasar Sukatani"));

            googleMap.addMarker(new MarkerOptions().position(PSGOMB)
                    .title("Pasir Gombong"));
            googleMap.addMarker(new MarkerOptions().position(PSGOMB)
                    .snippet("Lokasi Terdekat: 1. Kp Pulo, 2.Kp Jagawana, 3.Perum"));

			googleMap.addMarker(new MarkerOptions().position(CIBARUSAH)
					.title("Cibarusah"));
            googleMap.addMarker(new MarkerOptions().position(CIBARUSAH)
                    .snippet("Lokasi Terdekat: 1. Jl.Gatsoe, 2.Bank BNI, 3. SMAN 1"));

			googleMap.addMarker(new MarkerOptions().position(LIPPO)
					.title("Lippo"));
            googleMap.addMarker(new MarkerOptions().position(LIPPO)
                    .snippet("Lokasi Terdekat: 1. Pasar, 2.Lippo, 3.Naga Supermarket"));

		}
	}

	private class ReadTask extends AsyncTask<String, Void, String> {
		@Override
		protected String doInBackground(String... url) {
			String data = "";
			try {
				Maps_HttpConnection http = new Maps_HttpConnection();
				data = http.readUrl(url[0]);
			} catch (Exception e) {
				Log.d("Background Task", e.toString());
			}
			return data;
		}

		@Override
		protected void onPostExecute(String result) {
			super.onPostExecute(result);
			new ParserTask().execute(result);
		}
	}

	private class ParserTask extends
			AsyncTask<String, Integer, List<List<HashMap<String, String>>>> {

		@Override
		protected List<List<HashMap<String, String>>> doInBackground(
				String... jsonData) {

			JSONObject jObject;
			List<List<HashMap<String, String>>> routes = null;

			try {
				jObject = new JSONObject(jsonData[0]);
				Maps_PathJSONParser parser = new Maps_PathJSONParser();
				routes = parser.parse(jObject);
			} catch (Exception e) {
				e.printStackTrace();
			}
			return routes;
		}

		@Override
		protected void onPostExecute(List<List<HashMap<String, String>>> routes) {
			ArrayList<LatLng> points = null;
			PolylineOptions polyLineOptions = null;

			// traversing through routes
			for (int i = 0; i < routes.size(); i++) {
				points = new ArrayList<LatLng>();
				polyLineOptions = new PolylineOptions();
				List<HashMap<String, String>> path = routes.get(i);

				for (int j = 0; j < path.size(); j++) {
					HashMap<String, String> point = path.get(j);

					double lat = Double.parseDouble(point.get("lat"));
					double lng = Double.parseDouble(point.get("lng"));
					LatLng position = new LatLng(lat, lng);

					points.add(position);
				}

				polyLineOptions.addAll(points);
				polyLineOptions.width(5);
				polyLineOptions.color(Color.BLUE);
			}

			googleMap.addPolyline(polyLineOptions);
		}
	}
}
