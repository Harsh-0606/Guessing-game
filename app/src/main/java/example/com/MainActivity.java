package example.com;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import static java.lang.Integer.parseInt;

public class MainActivity extends AppCompatActivity {
    private EditText txtGuess;
    private Button btnGuess;
    private TextView lblOutput;
    private int theNumber;
    private int chances=7;
    private void newGame()
    {
        theNumber=(int)((Math.random())*100)+1;
        chances=7;
    }
    public void checkGuess()
    {
        String number= txtGuess.getText().toString();
        String message="";
        try
        {
            chances--;
            int guess= (int)parseInt(number);
            if(guess>theNumber)
            {
                message=guess+" too high you are lefft with "+ chances +" chances left";
                lblOutput.setText(message);
            }
            else if(guess<theNumber)
            {

                message=guess+" too low. "+ chances +" chances left";
                lblOutput.setText(message);
            }
            else
            {

                message=guess+" is the right guess";
                lblOutput.setText(message);
                newGame();

            }
            if(chances==1)
            {
                message="sorry you could not guess the right answer the correct number is "+theNumber;
                newGame();
            }
        }
        catch(Exception e)
        {
            message="please enter a whole number";
            lblOutput.setText(message);

        }
        finally
        {
            txtGuess.requestFocus();
            txtGuess.selectAll();
        }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtGuess= (EditText) findViewById(R.id.txtGuess);
        btnGuess= (Button) findViewById(R.id.btnGuess);
        lblOutput= (TextView) findViewById(R.id.lblOutput);

        newGame();
        btnGuess.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkGuess();
            }
        });

        // event listener for input field
        txtGuess.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                checkGuess();
                return false;
            }
        });
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
