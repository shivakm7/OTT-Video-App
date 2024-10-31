package canadore.com.ott_video_app
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.view.View
import android.widget.Button
import android.widget.EditText
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class AddEditScreenActivity : AppCompatActivity() {

    private lateinit var screenTitle: EditText
    private lateinit var uiComponents: EditText
    private lateinit var databaseReference: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_edit_screen)

        screenTitle = findViewById(R.id.screen_title)
        uiComponents = findViewById(R.id.ui_components)
        databaseReference = FirebaseDatabase.getInstance().getReference("storyboards")

        findViewById<Button>(R.id.save_button).setOnClickListener {
            saveScreenData()
        }
    }

    private fun saveScreenData() {
        val title = screenTitle.text.toString()
        val components = uiComponents.text.toString()
        val id = databaseReference.push().key

        val item = StoryboardItem(id, title, components)
        databaseReference.child(id!!).setValue(item)
        finish()
    }
}