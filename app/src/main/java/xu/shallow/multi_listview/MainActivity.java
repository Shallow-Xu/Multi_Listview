package xu.shallow.multi_listview;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import xu.shallow.multi_listview.adapter.Main_Adapter;

public class MainActivity extends AppCompatActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		ListView lv_main = (ListView) findViewById(R.id.lv_main);
		List<TransInfo> list = makeDebugData();
		Main_Adapter main_adapter = new Main_Adapter(MainActivity.this, list);
		lv_main.setAdapter(main_adapter);
	}

	private List<TransInfo> makeDebugData() {
		List<TransInfo> list = new ArrayList<>();
		for (int i = 0; i < 3; i++) {
			TransInfo transInfo = new TransInfo();
			Calendar cal = Calendar.getInstance();
			cal.add(Calendar.DATE, -i);
			String time = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(cal.getTime());
			transInfo.setOrder_time(time);
			for (int j = 0; j < 10; j++) {
				TransInfo info = new TransInfo();
				info.setOrder_time(transInfo.getOrder_time());
				info.setGoods_name("商品" + i + j);
				info.setGoods_amount(Util.random(5));
				info.setOrder_id(Util.random(20));
				info.setOrder_time(transInfo.getOrder_time());
				list.add(info);
			}
		}
		return list;
	}
}
