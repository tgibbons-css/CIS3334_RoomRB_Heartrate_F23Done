package css.cis3334roomrbheartrate;

import android.app.Application;
import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import java.util.ArrayList;
import java.util.List;
public class MainViewModel extends AndroidViewModel {

    private HeartrateRepository heartrateRepository;
    private List<Heartrate> heartrateList;

    public MainViewModel(@NonNull Application application) {
        super(application);
        heartrateRepository = new HeartrateRepository(application);
        heartrateList = new ArrayList<Heartrate>();
        //heartrateList = heartrateRepository.getAllHeartrates();
    }

    List<Heartrate> getAllHeartrates() {
        heartrateList = heartrateRepository.getAllHeartrates();
        return heartrateList;
    }

    public Heartrate getHeartrate(Integer position) {
        return heartrateRepository.getHeartrate(position);
    }

    String getHeartratesAsString () {
        String retString = "Heart Rates \n";
        heartrateList = heartrateRepository.getAllHeartrates();
        for (Heartrate hr:heartrateList) {
            retString = retString +  hr.toString() +"\n";
        }
        return retString;
    }

    public Integer getNumberRates() {
        return heartrateRepository.getNumberRates();
    }

    public void insert(Integer heartRate, Integer age) {
        Heartrate hr = new Heartrate(heartRate, age);
        heartrateRepository.insert(hr);
    }

}
