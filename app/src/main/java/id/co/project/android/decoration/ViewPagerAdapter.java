package id.co.project.android.decoration;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;



public class ViewPagerAdapter extends PagerAdapter {

    Context context;
    int[] flag;
    String[] judul;
    String[] keterangan;
    LayoutInflater inflater;
    public ViewPagerAdapter(Context context, String[] judul, String[]
            keterangan,
                            int[] flag) {
        this.context = context;
        this.judul=judul;
        this.keterangan=keterangan;
        this.flag = flag;
    }

    @Override
    public int getCount() {

        return flag.length;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == ((LinearLayout) object);
    }
    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        TextView teksJudul;
        ImageView imgflag;
        TextView teksKeterangan;
        inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View itemView = inflater.inflate(R.layout.fragment_foto, container,
                false);

        ;


// Locate the ImageView in viewpager_item.xml
        imgflag = (ImageView) itemView.findViewById(R.id.imma);
// Capture position and set to the ImageView
        imgflag.setImageResource(flag[position]);
        // Add viewpager_item.xml to ViewPager
        ((ViewPager) container).addView(itemView);
        return itemView;
    }
    @Override
    public void destroyItem(ViewGroup container, int position, Object object)
    {
// Remove viewpager_item.xml from ViewPager
        ((ViewPager) container).removeView((LinearLayout) object);
    }


}
