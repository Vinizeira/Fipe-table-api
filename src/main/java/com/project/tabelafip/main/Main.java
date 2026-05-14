package com.project.tabelafip.main;

import com.project.tabelafip.model.Brand;
import com.project.tabelafip.model.Model;
import com.project.tabelafip.model.ModelList;
import com.project.tabelafip.model.Vehicle;
import com.project.tabelafip.service.FipeService;
import com.project.tabelafip.util.ApiConsumer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;

import java.util.Scanner;
import java.util.stream.Collectors;

@Component
public class Main implements CommandLineRunner {

    @Autowired
    private ApiConsumer apiConsumer;
    @Autowired
    private FipeService fipeService;

    @Override
    public void run(String... args) throws Exception {
        Scanner sc = new Scanner(System.in);
        System.out.println("Escolha uma opcao:\n1 - Carros\n2 - Motos\n3 - Caminhoes");
        String opcao = sc.nextLine();

        String url = "";
        switch (opcao) {
            case "1" -> url = "https://parallelum.com.br/fipe/api/v1/carros/marcas";
            case "2" -> url = "https://parallelum.com.br/fipe/api/v1/motos/marcas";
            case "3" -> url = "https://parallelum.com.br/fipe/api/v1/caminhoes/marcas";
            default -> System.out.println("Opcao invalida!");
        }
        String json = apiConsumer.getData(url);
        List<Brand> brands = fipeService.parseJsonList(json,  Brand.class);
        brands.forEach(System.out::println);

        System.out.println("Digite o codigo da marca:");
        String brandCode = sc.nextLine();

        String modelsUrl = url + "/" + brandCode + "/modelos";
        String modelsJson = apiConsumer.getData(modelsUrl);
        ModelList modelList = fipeService.parseJson(modelsJson, ModelList.class);
        modelList.models().forEach(System.out::println);

        System.out.println("Digite um trecho do nome do modelo: ");
        String modelName = sc.nextLine();

        List<Model> filteredModels = modelList.models().stream()
                .filter(m -> m.name().toLowerCase().contains(modelName.toLowerCase()))
                .collect(Collectors.toList());
        filteredModels.forEach(System.out::println);

        System.out.println("Digite o codigo do modelo desejado: ");
        String modelCode = sc.nextLine();

        String yearsUrl = url + "/" + brandCode + "/modelos/" + modelCode + "/anos";
        String yearsJson = apiConsumer.getData(yearsUrl);
        List<Model> years = fipeService.parseJsonList(yearsJson, Model.class);
        years.forEach(System.out::println);
        years.forEach(year -> {
            String vehicleJson = null;
            try {
                vehicleJson = apiConsumer.getData(yearsUrl + "/" + year.code());
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            } catch (IOException e) {
                throw new RuntimeException(e);
            } catch (URISyntaxException e) {
                throw new RuntimeException(e);
            }
            Vehicle vehicle = fipeService.parseJson(vehicleJson, Vehicle.class);
            System.out.println(vehicle);
        });
    }
}
