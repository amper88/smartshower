package com.amper.smartshower;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.amper.smartshower.util.GlobalConfigurationSingleton;

public class ConfigurationActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_configuration);

        EditText editTextStartAction = (EditText) findViewById(R.id.EditTextNameStartAction);
        editTextStartAction.setText(GlobalConfigurationSingleton.getInstance().getUrlStartAction());

        EditText editTextGetHistoricalData = (EditText) findViewById(R.id.EditTextNameGetHistoricalData);
        editTextGetHistoricalData.setText(GlobalConfigurationSingleton.getInstance().getUrlGetRiegos());

        EditText edLatitud = (EditText) findViewById(R.id.EditTextLatitud);
        edLatitud.setText(""+GlobalConfigurationSingleton.getInstance().getLatitud());

        EditText edLongitud = (EditText) findViewById(R.id.EditTextLongitud);
        edLongitud.setText(""+GlobalConfigurationSingleton.getInstance().getLongitud());

        EditText edDistancia = (EditText) findViewById(R.id.EditTextDistancia);
        edDistancia.setText(""+GlobalConfigurationSingleton.getInstance().getDistancia());
    }

    public void saveConfigurationAction(View v) {
        EditText editTextStartAction = (EditText)findViewById(R.id.EditTextNameStartAction);
        GlobalConfigurationSingleton.getInstance().setUrlStartAction(editTextStartAction.getText().toString());

        EditText editTextGetHistoricalData = (EditText)findViewById(R.id.EditTextNameGetHistoricalData);
        GlobalConfigurationSingleton.getInstance().setUrlGetRiegos(editTextGetHistoricalData.getText().toString());

        EditText edLatitud = (EditText)findViewById(R.id.EditTextLatitud);
        GlobalConfigurationSingleton.getInstance().setLatitud(Double.parseDouble(edLatitud.getText().toString()));

        EditText edLongitud = (EditText)findViewById(R.id.EditTextLongitud);
        GlobalConfigurationSingleton.getInstance().setLongitud(Double.parseDouble(edLongitud.getText().toString()));

        EditText edDistancia = (EditText)findViewById(R.id.EditTextDistancia);
        GlobalConfigurationSingleton.getInstance().setDistancia(Double.parseDouble(edDistancia.getText().toString()));


        Toast.makeText(this.getBaseContext(), "Guardado",Toast.LENGTH_LONG).show();
    }

}
