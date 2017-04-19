package prog.teampoule.applitest.Activity;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.ViewStub;
import android.widget.VideoView;

import prog.teampoule.applitest.R;
import prog.teampoule.applitest.Utilities.Menu;

/**
 * Created by Noemie on 18/04/2017.
 */

public class activity_Tutoriels extends Menu {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ViewStub stub = (ViewStub) findViewById(R.id.layout_stub);
        stub.setLayoutResource(R.layout.activity_tutoriels);
        View inflated = stub.inflate();

        //VideoView videoView = (VideoView)findViewById(R.id.videoView);
        //MediaController mediaController = new MediaController(this);
        // mediaController.setAnchorView(videoView);
        //videoView.setMediaController(mediaController);

        //videoView.setVideoURI(Uri.parse("https://www.youtube.com/watch?v=BXpdmKELE1k"));

        //videoView.start();
    }
}
