package org.lunapark.develop.wedromedael.contacts;

import java.util.HashSet;
import java.util.Set;

import org.lunapark.develop.wedromedael.R;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract.CommonDataKinds;
import android.provider.ContactsContract.CommonDataKinds.Phone;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class WedroContacts extends Activity implements OnClickListener {

	public static final int REQUEST_SELECT_CONTACT = 1;

	ListView lvContacts;
	TextView tvName;

	String contactName = "Rambo";
	Uri contactUri;

	Set<String> uriSet, nameSet;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.contacts_activity);
		setTitle(getString(R.string.title_contacts));

		lvContacts = (ListView) findViewById(R.id.lvContacts);
		tvName = (TextView) findViewById(R.id.tvContact);

		tvName.setOnClickListener(this);

		uriSet = new HashSet<String>();

		nameSet = new HashSet<String>();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.menu_contacts, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {

		switch (item.getItemId()) {
		case R.id.action_add: // Add contact

			addContactFromAddressBook();

			break;

		default:
			break;
		}

		return super.onOptionsItemSelected(item);
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {

		super.onActivityResult(requestCode, resultCode, data);
		// Add contact
		if (requestCode == REQUEST_SELECT_CONTACT) {
			// Make sure the request was successful
			if (resultCode == RESULT_OK) {

				contactUri = data.getData();
				Log.e("Wedromeda", "Uri: " + contactUri.toString());
				contactName = getNameFromUri(contactUri);
/*
				if (!uriSet.contains(contactUri.toString())) {
					uriSet.add(contactUri.toString());
					nameSet.add(contactName);
					displayListView();
				}
*/
				uriSet.add(contactUri.toString());
				nameSet.add(contactName);
				displayListView();
				// Show name
				Toast.makeText(getApplicationContext(), contactName,
						Toast.LENGTH_LONG).show();
				tvName.setText(contactName);

			}
		}
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.tvContact:
			// viewContact(contactUri);
			break;

		default:
			break;
		}
	}

	private void viewContact(Uri uri) {
		Intent intent = new Intent(Intent.ACTION_VIEW, uri);
		startActivity(intent);
	}

	private void addContactFromAddressBook() {
		Intent intent = new Intent(Intent.ACTION_PICK);
		intent.setType(CommonDataKinds.Phone.CONTENT_TYPE);
		startActivityForResult(intent, REQUEST_SELECT_CONTACT);
	}

	/**
	 * Display contact-list
	 */
	private void displayListView() {
		String[] arrayNames = nameSet.toArray(new String[0]);
		
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(
				getApplicationContext(), R.layout.contacts_list_item,
				arrayNames);
		lvContacts.setAdapter(adapter);		

	}

	private String getNameFromUri(Uri uri) {
		String[] projection = new String[] { Phone.DISPLAY_NAME };
		Cursor cursor = getContentResolver().query(uri, projection, null, null,
				null);
		cursor.moveToFirst();
		int numberIndex = cursor.getColumnIndex(Phone.DISPLAY_NAME);
		return cursor.getString(numberIndex);

	}

	
}
