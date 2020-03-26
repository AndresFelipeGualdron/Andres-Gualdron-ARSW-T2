package edu.eci.arsw.coronavirus.persistencia;


import edu.eci.arsw.coronavirus.model.Country;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;

@Service
public class ActualizaService {

    private List<Country> paises = new ArrayList<>();

    public ActualizaService(){
        try {
            URL url = new URL("https://covid-19-coronavirus-statistics.p.rapidapi.com/v1/stats");
            URLConnection connection = url.openConnection();
            connection.setRequestProperty("x-rapidapi-host","covid-19-coronavirus-statistics.p.rapidapi.com");
            connection.setRequestProperty("x-rapidapi-key","6b4a736760msh2090ce99f78b9b3p1f43b2jsn8f383dbae7be");
            BufferedReader respuesta = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            JSONObject jsonObject = new JSONObject(respuesta.readLine());
            JSONArray countriesJson = jsonObject.getJSONObject("data").getJSONArray("covid19Stats");

            List<Country> lista = new ArrayList<>();

            for(int i = 0; i<countriesJson.length(); i++){
                JSONObject object = countriesJson.getJSONObject(i);
                String pais = object.getString("country");
                String provincia = object.getString("province");
                String ciudad = object.getString("city");
                int confirmados = object.getInt("confirmed");
                int muertos = object.getInt("deaths");
                int recuperados = object.getInt("recovered");

                String lastUpdate = object.getString("lastUpdate");
                String keyId = object.getString("keyId");
            }

            System.out.println(countriesJson);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public List<Country> getPaises(){
        return this.paises;
    }
}
