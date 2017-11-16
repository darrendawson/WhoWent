package app.whowent;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.HashMap;

public class EditPublicProfileActivity extends AppCompatActivity {

    static final int CAMERA_PIC_REQUEST = 1337;

    MemoryModel memory = new MemoryModel();

    //UI
    ImageButton editProfilePicture;
    EditText editName;
    EditText editBio;
    EditText editEmail;
    EditText editPhone;

    //==============================================================================================
    // START
    //==============================================================================================
    /*
        Functions for when the Activity boots up
     */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_public_profile);

        setUpIDs();
        setUpButtons();
    }

    //---setUpIDs-----------------------------------------------------------------------------------

    // sets up IDs of views
    void setUpIDs() {
        editName = (EditText)findViewById(R.id.edit_name);
        editBio = (EditText)findViewById(R.id.edit_bio);
        editEmail = (EditText)findViewById(R.id.edit_email);
        editPhone = (EditText)findViewById(R.id.edit_phone_number);
    }

    //---setUpButtons-------------------------------------------------------------------------------

    // creates onClick() functions for buttons
    void setUpButtons() {

        HashMap<String, String> info = memory.readPublicProfileFromMemory(this);
        editName.setText(info.get(memory.nameKey_PUBLIC));
        editBio.setText(info.get(memory.bioKey_PUBLIC));
        editEmail.setText(info.get(memory.emailKey_PUBLIC));
        editPhone.setText(info.get(memory.phoneKey_PUBLIC));

        editProfilePicture = (ImageButton)findViewById(R.id.edit_profile_pic);
        editProfilePicture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // open up camera
                Intent cameraIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
                cameraIntent.putExtra("android.intent.extras.USE_FRONT_CAMERA", true);
                startActivityForResult(cameraIntent, CAMERA_PIC_REQUEST);
            }
        });

    }

    //---onActivityResults--------------------------------------------------------------------------

    // collects data from finished Activities
    // In this case, getting the picture taken by Camera activity for profile picture
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == CAMERA_PIC_REQUEST) {
            if (data!= null) {
                Bitmap image = (Bitmap) data.getExtras().get("data");
                editProfilePicture.setImageBitmap(image);
            }
        }
    }


    //==============================================================================================
    // END
    //==============================================================================================
    /*
        Functions for when closing the activity
        Mostly saving stuff
     */

    //---onStop-------------------------------------------------------------------------------------

    // save unsaved shit
    @Override
    protected void onStop() {
        super.onStop();

        HashMap<String, String> map = new HashMap<>();
        map.put(memory.nameKey_PUBLIC, editName.getText().toString());
        map.put(memory.bioKey_PUBLIC, editBio.getText().toString());
        map.put(memory.emailKey_PUBLIC, editEmail.getText().toString());
        map.put(memory.phoneKey_PUBLIC, editPhone.getText().toString());

        memory.savePublicProfileToMemory(this, map);

        toast("saving...");
    }



    //==============================================================================================
    // Utilities
    //==============================================================================================
    /*
        Helper functions
     */

    //---toast--------------------------------------------------------------------------------------

    // toasts
    public void toast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();


    }
}
