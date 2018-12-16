package com.fairymo.macrunnerpickupsystem.activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.fairymo.macrunnerpickupsystem.R;
import com.fairymo.macrunnerpickupsystem.constants.Constant;
import com.fairymo.macrunnerpickupsystem.utils.SharedPreferencesUtil;
import com.fairymo.macrunnerpickupsystem.utils.StringUtil;
import com.fairymo.macrunnerpickupsystem.views.CustomTextInputEditText;

public class SettingActivity extends BaseActivity {

	@BindView(R.id.set_shopping_id)
	CustomTextInputEditText setShoppingId;
	@BindView(R.id.set_brand_no)
	CustomTextInputEditText setBrandNo;
	@BindView(R.id.submit)
	Button submit;

	@SuppressLint("CheckResult")
	@Override
	protected void init() {
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
			WindowManager.LayoutParams.FLAG_FULLSCREEN);
		setContentView(R.layout.activity_settings);
		ButterKnife.bind(this);
		submit.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				String shoppingNo, brandNo;
				if (StringUtil.isEmpty(shoppingNo = setShoppingId.getText().toString())) {
					Snackbar.make(v, R.string.fill_shopping_no, Snackbar.LENGTH_SHORT).show();
					return;
				}
				if (StringUtil.isEmpty(brandNo = setBrandNo.getText().toString())) {
					Snackbar.make(v, R.string.fill_brand_no, Snackbar.LENGTH_SHORT).show();
					return;
				}
				SharedPreferencesUtil.setString(SettingActivity.this, Constant.SHOPPING_NO, shoppingNo);
				SharedPreferencesUtil.setString(SettingActivity.this, Constant.BRAND_NO, brandNo);
				SharedPreferencesUtil.setBoolean(SettingActivity.this, Constant.SHOPPING_ID_SET, true);
				Intent intent = new Intent(SettingActivity.this, MainActivity.class);
				startActivity(intent);
				finish();
			}
		});
	}
}
