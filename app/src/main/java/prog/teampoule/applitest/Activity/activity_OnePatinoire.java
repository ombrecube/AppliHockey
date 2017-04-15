package prog.teampoule.applitest.Activity;

import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.ViewStub;
import android.widget.TextView;

import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import prog.teampoule.applitest.R;
import prog.teampoule.applitest.classAdapter.Patinoire;

/**
 * Created by Julien on 14/04/2017.
 */

public class activity_OnePatinoire extends prog.teampoule.applitest.Utilities.Menu implements OnMapReadyCallback{

    MapView mapView;
    GoogleMap map;
    double lat;
    double longi;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ViewStub stub = (ViewStub) findViewById(R.id.layout_stub);
        stub.setLayoutResource(R.layout.activity_patinoire_details);
        View inflated = stub.inflate();
        Patinoire patinoire = getIntent().getExtras().getParcelable("patinoire");
        Log.d("Patinoire", patinoire.getNom());

        TextView nom = (TextView) findViewById(R.id.nom_patinoire);
        nom.setText(patinoire.getNom());

        TextView adresse = (TextView) findViewById(R.id.adresse_patinoire);
        adresse.setText(patinoire.getAdresse());

        TextView tel = (TextView) findViewById(R.id.telephone_patinoire);
        tel.setText(patinoire.getTelephone());

        TextView cp = (TextView) findViewById(R.id.cp_patinoire);
        cp.setText(patinoire.getCp());

        lat = 48;
        longi = -74;

        mapView = (MapView) findViewById(R.id.mapView);
        mapView.onCreate(savedInstanceState);
        mapView.getMapAsync(this);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        map = googleMap;
        LatLng sydney = new LatLng(lat, longi);
        map.addMarker(new MarkerOptions().position(sydney).title("La patinoire"));
        map.moveCamera(CameraUpdateFactory.newLatLngZoom(sydney,15));
        map.setMapType(GoogleMap.MAP_TYPE_NORMAL);

    }
}