package org.lunapark.develop.wedromedael;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.lunapark.develop.wedromedael.contacts.WedroContacts;
import org.lunapark.develop.wedromedael.db.WedroDatabase;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ExpandableListView;
import android.widget.ExpandableListView.OnChildClickListener;
import android.widget.SimpleExpandableListAdapter;

public class MainActivity extends Activity implements OnChildClickListener {
		
	private ArrayList<String> listProjects;
	WedroDatabase databaseHelper;
	
	// коллекция для групп
	ArrayList<Map<String, String>> dataProjects;
	// общая коллекция для коллекций элементов
	ArrayList<ArrayList<Map<String, String>>> childData;
	// коллекция для элементов одной группы
	ArrayList<Map<String, String>> childDataItem;
	
	// список аттрибутов группы или элемента
	Map<String, String> m;
	
	
	
	

	ExpandableListView elvMain;
	
	/**
	 * Выход из программы
	 */
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		// Handle the back button
		if (keyCode == KeyEvent.KEYCODE_BACK) {
			// Ask the user if they want to quit
			new AlertDialog.Builder(this)
					// .setIcon(android.R.drawable.ic_dialog_alert)
					.setTitle(getString(R.string.app_name))
					.setMessage(getString(R.string.title_quit))
					.setPositiveButton(android.R.string.yes,
							new DialogInterface.OnClickListener() {
								@Override
								public void onClick(DialogInterface dialog,
										int which) {
									finish();
								}

							}).setNegativeButton(android.R.string.no, null)
					.show();

			return true;
		} else {
			return super.onKeyDown(keyCode, event);
		}

	}


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main_activity);
		
		elvMain = (ExpandableListView) findViewById(R.id.elvMain);
		elvMain.setOnChildClickListener(this);

		
		
		// Add project titles to list
		listProjects = new ArrayList<String>();
		databaseHelper = new WedroDatabase();		
		listProjects.addAll(databaseHelper.getTitleProjects());
		
		
		dataProjects =new ArrayList<Map<String, String>>();
		
		
		String groupProjectTag = "projectName";
		String groupElementTag = "elementName";
		String[] titleElements = new String[] {
				getString(R.string.title_task),
				getString(R.string.title_contacts),
				getString(R.string.title_notes),
				getString(R.string.title_events)
				};
		
		for (String str : listProjects) {
			Log.e("Wedro", str);
			m = new HashMap<String, String>();
			m.put(groupProjectTag, str); // Project name
			dataProjects.add(m);
		}
		
		String groupFrom[] = new String[] { groupProjectTag };
		int groupTo[] = new int[] { android.R.id.text1 };
		childData = new ArrayList<ArrayList<Map<String, String>>>();
		
		// создаем коллекцию элементов для первой группы
		
		        
		for (int i = 0; i < listProjects.size(); i++) {
			// заполняем список аттрибутов для каждого элемента
			childDataItem = new ArrayList<Map<String, String>>();
			for (String elements : titleElements) {
				m = new HashMap<String, String>();
				m.put(groupElementTag, elements); // название пункта
				childDataItem.add(m);
			}
			// добавляем в коллекцию коллекций
			childData.add(childDataItem);
		}
		
		// список аттрибутов элементов для чтения
		String childFrom[] = new String[] { groupElementTag };
		// список ID view-элементов, в которые будет помещены аттрибуты
		// элементов
		int childTo[] = new int[] { android.R.id.text1 };
		
		SimpleExpandableListAdapter adapter = new SimpleExpandableListAdapter(
				this, dataProjects,
				android.R.layout.simple_expandable_list_item_1, groupFrom,
				groupTo, childData, android.R.layout.simple_list_item_1,
				childFrom, childTo);

		
		elvMain.setAdapter(adapter);
		

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
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	@Override
	public boolean onChildClick(ExpandableListView parent, View v,
			int groupPosition, int childPosition, long id) {
		// TODO 
		Log.e("Wedro", "Group: " + groupPosition + " child: " + childPosition + " id: " + id);
		
		switch (childPosition) {
		case 1:
			// Contacts
			Intent intent = new Intent(this, WedroContacts.class);
			startActivity(intent);
			break;

		default:
			break;
		}
		return false;
	}

	

}
