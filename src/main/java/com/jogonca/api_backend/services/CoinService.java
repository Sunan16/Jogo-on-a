package com.jogonca.api_backend.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jogonca.api_backend.interfaces.AbstractCrudService;
import com.jogonca.api_backend.models.Coin;
import com.jogonca.api_backend.models.dtos.receiveDTOs.CoinDTO;
import com.jogonca.api_backend.models.dtos.sendDTOs.CoinSendDTO;
import com.jogonca.api_backend.repositories.CoinRepository;

@Service
public class CoinService extends AbstractCrudService<CoinDTO, Coin, CoinSendDTO, String>{

    public CoinService(@Autowired CoinRepository repository) {
        super(Coin.class.getName(), repository, Coin.class, CoinDTO.class);
    }

    protected void updateData(Coin entity, CoinDTO i) {
        if(i.getCoins() != null){
            entity.setCoins(i.getCoins());
        }else{
            entity.setCoins(0L);
        }
        
    }

}
