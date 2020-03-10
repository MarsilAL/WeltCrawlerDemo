package weltcrawlerdemo;

import java.util.Date;

import java.util.TimerTask;
import weltcrawlerdemo.domain.*;

public class FetchAndStoreTask extends TimerTask {

    private StorageUseCase storageUseCase;

    public FetchAndStoreTask(StorageUseCase storageUseCase) {
        this.storageUseCase = storageUseCase;
    }

    @Override
    public void run() {
        System.out.println("started at:" + new Date());
        storageUseCase.fetchAndStoreArticles();
        System.out.println("finished at:" + new Date());
    }
}