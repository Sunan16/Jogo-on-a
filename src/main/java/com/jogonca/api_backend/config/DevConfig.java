package com.jogonca.api_backend.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.jogonca.api_backend.controllers.CategoryController;
import com.jogonca.api_backend.controllers.HabilityController;
import com.jogonca.api_backend.controllers.ItemController;
import com.jogonca.api_backend.controllers.LoginController;
import com.jogonca.api_backend.controllers.UserController;
import com.jogonca.api_backend.models.dtos.receiveDTOs.CategoryDTO;
import com.jogonca.api_backend.models.dtos.sendDTOs.AddItemUser;
import com.jogonca.api_backend.models.dtos.sendDTOs.CategorySendDTO;
import com.jogonca.api_backend.models.dtos.sendDTOs.HabilitySendDTO;
import com.jogonca.api_backend.models.dtos.sendDTOs.ItemSendDTO;
import com.jogonca.api_backend.models.dtos.sendDTOs.UserSendDTO;

@Configuration
@Profile("dev")
public class DevConfig implements CommandLineRunner {

    @Autowired
    private CategoryController categoryController;
    @Autowired
    private HabilityController habilityController;
    @Autowired
    private ItemController itemController;
    @Autowired
    private UserController userController;
    @Autowired
    private LoginController loginController;

    @Override
    public void run(String... args) {
        try {
            System.out.println("************************************************************");
            Thread.sleep(250);
            System.out.println("*----------------------------------------------------------*");
            Thread.sleep(250);
            System.out.println("*                                                          *");
            Thread.sleep(250);
            System.out.println("*----------------------------------------------------------*");
            Thread.sleep(250);
            System.out.println("*                 AMBIENTE DE DESENVOLVIMENTO              *");
            Thread.sleep(250);
            System.out.println("*----------------------------------------------------------*");
            Thread.sleep(250);
            System.out.println("*                                                          *");
            Thread.sleep(250);
            System.out.println("*----------------------------------------------------------*");
            Thread.sleep(250);
            System.out.println("************************************************************");

            Thread.sleep(2000);

            UserSendDTO u1 = new UserSendDTO(null, "Pedro", "pedrobraoliveira@gmail.com", null, "jogodaonca2025", null, null);
            UserSendDTO u2 = new UserSendDTO(null, "Teste", "teste@gmai.com", null, "BackendTest", null, null);
            System.out.println(userController.create(u1));
            System.out.println(userController.create(u2));

            CategorySendDTO c1 = new CategorySendDTO("Utilizaveis", "Itens consumiveis em cada partida");
            CategorySendDTO c2 = new CategorySendDTO("Skins", "Itens de uso para aparencia do personagem");
            CategorySendDTO c3 = new CategorySendDTO("Backgrounds", "Fundo de tela do personagem");
            CategoryDTO dto1 = categoryController.create(c1);
            CategoryDTO dto2 = categoryController.create(c2);
            CategoryDTO dto3 = categoryController.create(c3);

            HabilitySendDTO h1 = new HabilitySendDTO("Vida extra", "Da uma oportunidade nova para um jogador\nCachorro: restaura 3 cachorros\nOnça: da uma oportunidade a mais", "");
            HabilitySendDTO h2 = new HabilitySendDTO("Defesa de espinho", "", null);
            habilityController.create(h1);
            habilityController.create(h2);

            ItemSendDTO i1 = new ItemSendDTO("Espinho", "Utilizado para dar uma nova chance ao cachorro", null, "/foto1.png", dto1.getKey());
            ItemSendDTO i2 = new ItemSendDTO("Camiseta", "Item com cor personalizavel", null, "/foto2.png", dto2.getKey());
            ItemSendDTO i3 = new ItemSendDTO("Fundo Floresta", "Fundo de imagem", null, "/foto3.png", dto3.getKey());
            itemController.create(i1);
            itemController.create(i2);
            itemController.create(i3);

            loginController.addItem(new AddItemUser("pedrobraoliveira@gmail.com", "Espinho"));

        } catch (Exception e) {
            
        }
    }

}
