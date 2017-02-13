package xu.shallow.multi_listview.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.List;

import xu.shallow.multi_listview.Expandable.ExpandableLayoutItem;
import xu.shallow.multi_listview.R;
import xu.shallow.multi_listview.TransInfo;
import xu.shallow.multi_listview.Util;

/**
 * Created by Shallow Xu on 2017/2/5.
 */

public class Data_List_Adapter extends BaseAdapter {

	public List<TransInfo> list;

	private Context context;

	public Data_List_Adapter(Context mContext, List<TransInfo> list) {
		this.context = mContext;
		this.list = list;
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return list.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return list.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public View getView(final int position, View convertView, final ViewGroup parent) {
		if (convertView == null) {
			convertView = LayoutInflater.from(context).inflate(
					R.layout.view_data_item, parent, false);
		}
		final ExpandableLayoutItem eli_row = BaseViewHolder.get(convertView, R.id.eli_row);
		TextView goods_name = BaseViewHolder.get(convertView, R.id.goods_name);
		TextView goods_amount = BaseViewHolder.get(convertView, R.id.goods_amount);
		TextView order_id = BaseViewHolder.get(convertView, R.id.order_id);
		TextView order_time = BaseViewHolder.get(convertView, R.id.order_time);

		eli_row.setOnExpendItemChangeListener(new ExpandableLayoutItem.onExpendItemChangeListener() {
			@Override
			public void onChange(boolean b) {
				Util.setListViewHeightBasedOnChildren((ListView) parent);
			}
		});
		goods_name.setText(list.get(position).getGoods_name());
		String amount = list.get(position).getGoods_amount();
		String fmt_amount = String.valueOf(Float.valueOf(amount) / 100);
		goods_amount.setText("￥ " + fmt_amount);
		order_id.setText(context.getString(R.string.order_id) + ":" + list.get(position).getOrder_id());
		order_time.setText(context.getString(R.string.order_time) + ":" + list.get(position).getOrder_time());
		return convertView;
	}

}
