package id.sch.smktelkom_mlg.project.xirpl206162636.fastlaundry;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.widget.Spinner;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class Menu2 extends Fragment implements View.OnClickListener{

	Spinner paket;
	ArrayList<String> pakets = new ArrayList<String>();
	private Button buttonSave;
	private FirebaseAuth firebaseAuth;
	private DatabaseReference databaseReference;
	private EditText editTextAddress, editTextName, editTextPhone;
	ProfileActivity pa = new ProfileActivity();

	@Nullable
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View myView = inflater.inflate(R.layout.activity_profile, container,false);

		firebaseAuth = FirebaseAuth.getInstance();

		if(firebaseAuth.getCurrentUser() == null){
			pa.finish();
			startActivity(new Intent(getActivity(), LoginActivity.class));
		}
		databaseReference = FirebaseDatabase.getInstance().getReference();
		FirebaseUser user = firebaseAuth.getCurrentUser();

		editTextAddress = (EditText) myView.findViewById(R.id.editTextAddress);
		editTextName = (EditText) myView.findViewById(R.id.editTextName);
		editTextPhone = (EditText) myView.findViewById(R.id.editTextPhone);
		buttonSave = (Button) myView.findViewById(R.id.buttonSave);

		buttonSave.setOnClickListener(this);
		return myView;
	}

	private void saveUserInformation() {
		//Getting values from database
		String name = editTextName.getText().toString().trim();
		String add = editTextAddress.getText().toString().trim();
		String phone = editTextPhone.getText().toString().trim();

		//creating a userinformation object
		UserInformation userInformation = new UserInformation(name, add, phone);

		//getting the current logged in user
		FirebaseUser user = firebaseAuth.getCurrentUser();


		databaseReference.child("order").child(name).setValue(userInformation);

		//displaying a success toast
		Toast.makeText(getContext(), "Order Saved...", Toast.LENGTH_LONG).show();

		editTextName.setText(null);
		editTextAddress.setText(null);
		editTextPhone.setText(null);
	}

	@Override
	public void onClick(View view) {
		if(view == buttonSave){
			saveUserInformation();
		}
	}

	@Override
	public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
		super.onViewCreated(view, savedInstanceState);
		//you can set the title for your toolbar here for different fragments different titles
		getActivity().setTitle("Order");
	}
}