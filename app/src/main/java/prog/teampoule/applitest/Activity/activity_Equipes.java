package prog.teampoule.applitest.Activity;

import android.os.Bundle;
import android.view.View;
import android.view.ViewStub;
import android.widget.Button;

import prog.teampoule.applitest.Utilities.Menu;
import prog.teampoule.applitest.R;

/**
 * Created by Julien on 23/02/2017.
 */

public class activity_Equipes extends Menu {

    private Button b = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ViewStub stub = (ViewStub) findViewById(R.id.layout_stub);
        stub.setLayoutResource(R.layout.activity_equipes);
        View inflated = stub.inflate();




    }


}
