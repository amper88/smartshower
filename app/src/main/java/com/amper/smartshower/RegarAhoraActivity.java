package com.amper.smartshower;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.widget.TextView;
import android.util.Log;

import com.amper.smartshower.rest.RestUtil;
import com.amper.smartshower.util.GlobalConfigurationSingleton;

public class RegarAhoraActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d("NavigationItemSelected", "ASD ");
        System.out.println("HOLA");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_regar_ahora);

        this.doRegarAhora();
    }

    // stuff
    public void doRegarAhora() {
        TextView t = (TextView)findViewById(R.id.txtRegando);
        t.setText("¡¡REGANDO!!");

        WebView wv = (WebView)findViewById(R.id.webViewRegando);
        wv.setBackgroundColor(Color.TRANSPARENT);
        wv.loadUrl("file:///android_asset/regando.gif");


        String data = RestUtil.getJSON(GlobalConfigurationSingleton.getInstance().getUrlStartAction(), 3000);
        Log.d("doRegarAhora", "TERMINE GET JSON EN:"+GlobalConfigurationSingleton.getInstance().getUrlStartAction());
        if(data == null){
            Log.d("doRegarAhora", "REQUEST FAILED");
        }
    }

    public void regarAhoraAction(View v) {
        this.doRegarAhora();
    }

}
