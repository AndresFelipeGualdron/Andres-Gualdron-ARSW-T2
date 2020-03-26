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
import java.util.concurrent.atomic.AtomicBoolean;

@Service
public class ActualizaService {

    private List<Country> paises = new ArrayList<>();

    public ActualizaService(){
        actualizar();
    }


    public void actualizar(){
        this.paises = new ArrayList<>();
        try {
            URL url = new URL("https://covid-19-coronavirus-statistics.p.rapidapi.com/v1/stats");
            URLConnection connection = url.openConnection();
            connection.setRequestProperty("x-rapidapi-host","covid-19-coronavirus-statistics.p.rapidapi.com");
            connection.setRequestProperty("x-rapidapi-key","6b4a736760msh2090ce99f78b9b3p1f43b2jsn8f383dbae7be");
            BufferedReader respuesta = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            JSONObject jsonObject = new JSONObject(respuesta.readLine());
            JSONArray countriesJson = jsonObject.getJSONObject("data").getJSONArray("covid19Stats");


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
                    city.setNombre(ciudad);
                    city.setContagiados(confirmados);
                    city.setMuertos(muertos);
                    city.setRecuperados(recuperados);
                    city.setLastUpdate(lastUpdate);
                    city.setKeyId(keyId);

                    AtomicBoolean estaPais = new AtomicBoolean(false);
                    this.paises.forEach(k -> {
                        if(k.getNombre().equals(pais)){
                            estaPais.set(true);
                            k.setContagiados(k.getContagiados() + confirmados);
                            k.setMuertos(k.getMuertos() + muertos);
                            k.setRecuperados(k.getRecuperados() + recuperados);
                            k.setLastUpdate(lastUpdate);
                            AtomicBoolean estaProvince = new AtomicBoolean(false);
                            k.getProvinces().forEach(s -> {
                                if(s.getNombre().equals(province.getNombre())){
                                    estaProvince.set(true);
                                    AtomicBoolean estaCity = new AtomicBoolean(false);
                                    s.getCities().forEach(c -> {
                                        if(c.getNombre().equals(city.getNombre())){
                                            estaCity.set(true);
                                            c.setContagiados(c.getContagiados() + city.getContagiados());
                                            c.setMuertos(c.getMuertos() + city.getMuertos());
                                            c.setRecuperados(c.getRecuperados() + city.getRecuperados());
                                            s.setContagiados(s.getContagiados() + c.getContagiados());
                                            s.setMuertos(s.getMuertos() + c.getMuertos());
                                            s.setRecuperados(s.getRecuperados() + c.getRecuperados());
                                        }
                                    });
                                    if(!estaCity.get()){
                                        s.addCity(city);
                                        s.setContagiados(s.getContagiados() + city.getContagiados());
                                        s.setMuertos(s.getMuertos() + city.getMuertos());
                                        s.setRecuperados(s.getRecuperados() + city.getRecuperados());
                                    }

                                }
                                if(!estaProvince.get()){
                                    province.addCity(city);
                                    province.setContagiados(city.getContagiados());
                                    province.setMuertos(city.getMuertos());
                                    province.setRecuperados(city.getRecuperados());
                                    province.setKeyId(city.getKeyId());
                                    province.setLastUpdate(city.getLastUpdate());
                                    k.addProvince(province);
                                }
                            });
                        }
                    });
                    if(!estaPais.get()){
                        Country co = new Country();
                        co.addProvince(province);
                        co.setContagiados(city.getContagiados());
                        co.setMuertos(city.getMuertos());
                        co.setRecuperados(city.getRecuperados());
                        co.setKeyId(city.getKeyId());
                        co.setLastUpdate(city.getLastUpdate());
                        this.paises.add(co);
                    }
                }
                else if(!provincia.equals("")){
                    province.setNombre(provincia);
                    province.setContagiados(confirmados);
                    province.setMuertos(muertos);
                    province.setRecuperados(recuperados);
                    province.setLastUpdate(lastUpdate);
                    province.setKeyId(keyId);
                    AtomicBoolean estaPais = new AtomicBoolean(false);
                    this.paises.forEach(k -> {
                        if (k.getNombre().equals(pais)) {
                            estaPais.set(true);
                            k.setContagiados(k.getContagiados() + confirmados);
                            k.setMuertos(k.getMuertos() + muertos);
                            k.setRecuperados(k.getRecuperados() + recuperados);
                            k.setLastUpdate(lastUpdate);
                            AtomicBoolean estaProvince = new AtomicBoolean(false);
                            k.getProvinces().forEach(s -> {
                                if (s.getNombre().equals(province.getNombre())) {
                                    estaProvince.set(true);
                                    s.setContagiados(confirmados);
                                    s.setMuertos(muertos);
                                    s.setRecuperados(recuperados);
                                }
                            });
                            if(!estaProvince.get()){
                                k.addProvince(province);
                                k.setContagiados(k.getContagiados() + province.getContagiados());
                                k.setMuertos(k.getMuertos() + province.getMuertos());
                                k.setRecuperados(k.getRecuperados() + province.getRecuperados());
                            }

                        }
                    });
                    if(!estaPais.get()){
                        Country co = new Country();
                        co.addProvince(province);
                        co.setNombre(pais);
                        co.setContagiados(province.getContagiados());
                        co.setMuertos(province.getMuertos());
                        co.setRecuperados(province.getRecuperados());
                        co.setKeyId(province.getKeyId());
                        co.setLastUpdate(province.getLastUpdate());
                        this.paises.add(co);
                    }

                }
                else{
                    AtomicBoolean estaPais = new AtomicBoolean(false);
                    this.paises.forEach(k -> {
                        if(k.getNombre().equals(pais)){
                            estaPais.set(true);
                            k.setContagiados(k.getContagiados() + confirmados);
                            k.setMuertos(k.getMuertos() + muertos);
                            k.setRecuperados(k.getRecuperados() + recuperados);
                        }
                    });
                    if(!estaPais.get()){
                        Country co = new Country();
                        co.setNombre(pais);
                        co.setContagiados(confirmados);
                        co.setMuertos(muertos);
                        co.setRecuperados(recuperados);
                        co.setKeyId(keyId);
                        co.setLastUpdate(lastUpdate);
                        this.paises.add(co);
                    }
                }

            }

//            System.out.println(this.paises);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public List<Country> getPaises(){
        return this.paises;
//        return new ArrayList<>();
    }
}
