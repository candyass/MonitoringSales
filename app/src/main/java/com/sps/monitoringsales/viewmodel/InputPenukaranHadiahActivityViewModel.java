package com.sps.monitoringsales.viewmodel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

import com.sps.monitoringsales.SalesRepository;
import com.sps.monitoringsales.database.entity.Penukaran;
import com.sps.monitoringsales.database.entity.PenukaranHadiah;

import java.util.List;

/**
 * Created by sigit on 18/06/2018.
 */

public class InputPenukaranHadiahActivityViewModel extends AndroidViewModel {

    SalesRepository repository;

    public InputPenukaranHadiahActivityViewModel(@NonNull Application application) {
        super(application);
        repository = SalesRepository.getInstance(application);
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        repository = null;
    }

    public LiveData<Penukaran> getPenukaran(int idPenukaran) {
        return repository.getPenukaran(idPenukaran);
    }

    public void tukarHadiah(Penukaran penukaran, List<PenukaranHadiah> listPenukaranHadiah) {
        repository.savePenukaranHadiah(penukaran, listPenukaranHadiah);
    }
}
