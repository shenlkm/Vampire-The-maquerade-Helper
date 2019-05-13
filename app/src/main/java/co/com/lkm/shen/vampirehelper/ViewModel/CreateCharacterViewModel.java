package co.com.lkm.shen.vampirehelper.ViewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import javax.inject.Inject;

import co.com.lkm.shen.vampirehelper.Data.Domain.Entities.Player;
import co.com.lkm.shen.vampirehelper.Data.Repository.PlayerRepository;

public class CreateCharacterViewModel extends AndroidViewModel {

    public PlayerRepository mPlayerRepository;

    @Inject
    public CreateCharacterViewModel(@NonNull PlayerRepository playerRepository,@NonNull Application application) {
        super(application);
        mPlayerRepository = playerRepository;
    }

    public void insert(Player item){
        mPlayerRepository.insert(item);
    }
}
