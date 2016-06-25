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
        editTextStartAction.setHint("Ingrese URL de comienzo de riego");
        editTextStartAction.setText(GlobalConfigurationSingleton.getInstance().getUrlStartAction());

        EditText editTextGetHistoricalData = (EditText) findViewById(R.id.EditTextNameGetHistoricalData);
        editTextGetHistoricalData.setHint("Ingrese URL de datos historicos");
        editTextGetHistoricalData.setText(GlobalConfigurationSingleton.getInstance().getUrlGetRiegos());
    }

    public void saveConfigurationAction(View v) {
        EditText editTextStartAction = (EditText)findViewById(R.id.EditTextNameStartAction);
        GlobalConfigurationSingleton.getInstance().setUrlStartAction(editTextStartAction.getText().toString());

        EditText editTextGetHistoricalData = (EditText)findViewById(R.id.EditTextNameGetHistoricalData);
        GlobalConfigurationSingleton.getInstance().setUrlGetRiegos(editTextGetHistoricalData.getText().toString());

        Toast.makeText(this.getBaseContext(), "Guardado",Toast.LENGTH_LONG).show();
    }

}
