package edu.eci.arsw.coronavirus.persistencia;


import edu.eci.arsw.coronavirus.model.City;
import edu.eci.arsw.coronavirus.model.Country;
import edu.eci.arsw.coronavirus.model.Province;
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

                City city = new City();
                Province province = new Province();

                if(!ciudad.equals("")){
                    city.setContagiados(confirmados);
                    city.setMuertos(muertos);
                    city.setRecuperados(recuperados);
                    city.setLastUpdate(lastUpdate);
                    city.setKeyId(keyId);

                    boolean estaPais = false;
                    lista.forEach(k -> {
                        if(k.getNombre().equals(pais)){
                            k.setContagiados(k.getContagiados() + confirmados);
                            k.setMuertos(k.getMuertos() + muertos);
                            k.setRecuperados(k.getRecuperados() + recuperados);
                            k.setLastUpdate(lastUpdate);
                            boolean estaProvince = false;
                            k.getProvinces().forEach(s -> {
                                if(s.getNombre().equals(province.getNombre())){
                                    s.addCity(city);
                                    s.setContagiados(s.getContagiados() + city.getContagiados());
                                    s.setMuertos(s.getMuertos() + city.getMuertos());
                                    s.setRecuperados(s.getRecuperados() + city.getRecuperados());
                                }
                                if(!estaProvince){
                                    province.addCity(city);
                                    province.setContagiados(city.getContagiados());
                                    province.setMuertos(city.getMuertos());
                                    province.setRecuperados(city.getRecuperados());
                                    province.setKeyId(city.getKeyId());
                                    province.setLastUpdate(city.getLastUpdate());
                                }
                            });
                        }
                    });
                    if(!estaPais){
                        Country co = new Country();
                        co.addProvince(province);
                        co.setContagiados(city.getContagiados());
                        co.setMuertos(city.getMuertos());
                        co.setRecuperados(city.getRecuperados());
                        co.setKeyId(city.getKeyId());
                        co.setLastUpdate(city.getLastUpdate());
                    }
                }
                else if(!provincia.equals("")){

                    province.setContagiados(confirmados);
                    province.setMuertos(muertos);
                    province.setRecuperados(recuperados);
                    province.setLastUpdate(lastUpdate);
                    province.setKeyId(keyId);
                }
                else{

                }

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
