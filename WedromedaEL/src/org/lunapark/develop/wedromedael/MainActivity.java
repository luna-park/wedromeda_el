package org.lunapark.develop.wedromedael;

import java.util.ArrayList;

import org.lunapark.develop.wedromedael.contacts.WedroContacts;
import org.lunapark.develop.wedromedael.db.WedroDatabase;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ExpandableListView;

public class MainActivity extends Activity implements OnItemClickListener {
	
	//private String[] titleProjects = {"Проект 1", "Проект 4", "Проект 3", "Проект 7", "Проект 5"};
	private ArrayList<String> listProjects;
	private WedroDatabase databaseHelper = new WedroDatabase();
	
	String[] titleElements = null;
	/*
	// названия компаний (групп)
	  String[] groups = new String[] {"HTC", "Samsung", "LG"};
	  
	  // названия телефонов (элементов)
	  String[] phonesHTC = new String[] {"Sensation", "Desire", "Wildfire", "Hero"};
	  String[] phonesSams = new String[] {"Galaxy S II", "Galaxy Nexus", "Wave"};
	  String[] phonesLG = new String[] {"Optimus", "Optimus Link", "Optimus Black", "Optimus One"};
	  
	  // коллекция для групп
	  ArrayList<Map<String, String>> groupData;
	  
	  // коллекция для элементов одной группы
	  ArrayList<Map<String, String>> childDataItem;

	  // общая коллекция для коллекций элементов
	  ArrayList<ArrayList<Map<String, String>>> childData;
	  // в итоге получится childData = ArrayList<childDataItem>
	  
	  // список аттрибутов группы или элемента
	  Map<String, String> m;
	  */

	ExpandableListView elvMain;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main_activity);

		// Contacts
		Intent intent = new Intent(this, WedroContacts.class);
		//startActivity(intent);
		
		// Add project titles to list
		listProjects = new ArrayList<String>();			
		listProjects.addAll(databaseHelper.getTitleProjects());
		
		for (String str : listProjects) {
			Log.e("Wedro", str);
		}
		
		
/*		
		// заполняем коллекцию групп из массива с названиями групп
		groupData = new ArrayList<Map<String, String>>();
		for (String group : groups) {
			// заполняем список аттрибутов для каждой группы
			m = new HashMap<String, String>();
			m.put("groupName", group); // имя компании
			groupData.add(m);
		}

		// список аттрибутов групп для чтения
		String groupFrom[] = new String[] { "groupName" };
		// список ID view-элементов, в которые будет помещены аттрибуты
		// групп
		int groupTo[] = new int[] { android.R.id.text1 };

		// создаем коллекцию для коллекций элементов
		childData = new ArrayList<ArrayList<Map<String, String>>>();

		// создаем коллекцию элементов для первой группы
		childDataItem = new ArrayList<Map<String, String>>();
		// заполняем список аттрибутов для каждого элемента
		for (String phone : elementNames) {
			m = new HashMap<String, String>();
			m.put("phoneName", phone); // название телефона
			childDataItem.add(m);
		}
		// добавляем в коллекцию коллекций
		childData.add(childDataItem);

		// создаем коллекцию элементов для второй группы
		childDataItem = new ArrayList<Map<String, String>>();
		for (String phone : phonesSams) {
			m = new HashMap<String, String>();
			m.put("phoneName", phone);
			childDataItem.add(m);
		}
		childData.add(childDataItem);

		// создаем коллекцию элементов для третьей группы
		childDataItem = new ArrayList<Map<String, String>>();
		for (String phone : phonesLG) {
			m = new HashMap<String, String>();
			m.put("phoneName", phone);
			childDataItem.add(m);
		}
		childData.add(childDataItem);

		// список аттрибутов элементов для чтения
		String childFrom[] = new String[] { "phoneName" };
		// список ID view-элементов, в которые будет помещены аттрибуты
		// элементов
		int childTo[] = new int[] { android.R.id.text1 };


		SimpleExpandableListAdapter adapter = new SimpleExpandableListAdapter(
				this, groupData,
				android.R.layout.simple_expandable_list_item_1, groupFrom,
				groupTo, childData, android.R.layout.simple_list_item_1,
				childFrom, childTo);

		elvMain = (ExpandableListView) findViewById(R.id.elvMain);
		elvMain.setAdapter(adapter);
		elvMain.setOnItemClickListener(this);
		
		*/
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
	public void onItemClick(AdapterView<?> parent, View view, int position,
			long id) {
		Log.e("Wedromeda", "position: " + position);
		Log.e("Wedromeda", "id: " + id);
		
	}

}
