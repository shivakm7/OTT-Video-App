package canadore.com.ott_video_app
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ChildEventListener
import android.view.View
import android.widget.Button

class MainActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: StoryboardAdapter
    private lateinit var storyboardList: MutableList<StoryboardItem>
    private lateinit var databaseReference: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)

        recyclerView = findViewById(R.id.recycler_view)
        recyclerView.layoutManager = LinearLayoutManager(this)

        storyboardList = mutableListOf()
        adapter = StoryboardAdapter(storyboardList, this)
        recyclerView.adapter = adapter

        databaseReference = FirebaseDatabase.getInstance().getReference("storyboards")

        // Load data from Firebase
        databaseReference.addChildEventListener(object : ChildEventListener {
            override fun onChildAdded(snapshot: DataSnapshot, previousChildName: String?) {
                val item = snapshot.getValue(StoryboardItem::class.java)
                item?.let { storyboardList.add(it) }
                adapter.notifyDataSetChanged()
            }

            // Implement other required methods...
            override fun onChildChanged(snapshot: DataSnapshot, previousChildName: String?) {}
            override fun onChildRemoved(snapshot: DataSnapshot) {}
            override fun onChildMoved(snapshot: DataSnapshot, previousChildName: String?) {}
            override fun onCancelled(error: DatabaseError) {}
        })

        findViewById<Button>(R.id.add_button).setOnClickListener {
            startActivity(Intent(this, AddEditScreenActivity::class.java))
        }
    }
}