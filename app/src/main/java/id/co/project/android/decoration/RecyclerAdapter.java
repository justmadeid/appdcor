package id.co.project.android.decoration;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import static android.content.ContentValues.TAG;



public class RecyclerAdapter extends  RecyclerView.Adapter<RecyclerViewHolder> {

        //public TextView tv3;
    //deklarasi variable context

    private final Context context;
    // menampilkan list item dalam bentuk text dengan tipe data string dengan variable name
    String [] name={"Angsa Pink","Black PInk","Rainbow Birth","Pink Sweet Bridal","Pink Brwon Bridal","Birt Party"};
    int thumb []= {R.drawable.angas,R.drawable.hitam,R.drawable.rainbow,R.drawable.bridal,R.drawable.brown,R.drawable.ulangtahun};
    String[] desk = {"Harga : Rp 1.500.000,-\n" +
            "Dekorasi untuk pesta Ulang Tahun\n" +
            "List :\n"+
            "Color : Pink, Green White,yellow.  Mainan angsa 1 pasang. Dihiasi dengan balon-balon dan terdapat ornamen-ornamen yang mendukung  + Kue tart\n \n",

            "Harga : Rp 850.000,-\n" +
                    "Dekorasi untuk Ulang Tahun\n" +
                    "List :\n"+
                    "Color : dominan hitam. Balon full hitam, bunga-bunga\n \n",

            "Harga : Rp 900.000,-\n" +
                    "Dekorasi pesta ulang tahun dengan konsep penuh warna\n" +
                    "List :\n"+
                    "Color : Rainbow. Ornamen serba rainbow, Balon warna-warni + Rainbow Cake\n \n",

            "Harga : Rp 750.000,-\n" +
                    "Dekorasi Briddal Shower / Ulang tahun\n" +
                    "List :\n"+
                    " Color :  Golden + white, Balon-balon, pernak-pernik pendukung + snack \n\n",

            "Harga : Rp 1000.000,-\n" +
                    "Dekorasi Briddal Shower\n" +
                    "List : \n"+
                    "Color : Pink + White, Bunga-bunga + snack, kursi"};

    String [] gambar= {"angas.jpg","hitam.jpg","rainbow.jpg","bridal.jpg","brown.jpg","ulangtahun.jpg"};

    LayoutInflater inflater;
    public RecyclerAdapter(Context context) {
        this.context=context;
        inflater= LayoutInflater.from(context);
    }
    @Override
    public RecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v=inflater.inflate(R.layout.item_list, parent, false);

        RecyclerViewHolder viewHolder=new RecyclerViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerViewHolder holder, int position) {

        holder.tv1.setText(name[position]);
        // holder.tv3.setText(desk[position]);
        holder.tv1.setOnClickListener(clickListener);
        holder.imageView.setImageResource(thumb[position]);
        holder.imageView.setOnClickListener(clickListener);
        holder.tv2.setText(gambar[position]);
        //holder.tv3.setTag(holder);
        holder.tv1.setTag(holder);
        holder.imageView.setTag(holder);

    }

    View.OnClickListener clickListener=new View.OnClickListener() {
        @Override
        public void onClick(View v) {
//member aksi saat cardview diklik berdasarkan posisi tertentu
            RecyclerViewHolder vholder = (RecyclerViewHolder) v.getTag();

            int position = vholder.getPosition();




                Intent intent = new Intent(context, DetailWisata.class);
                intent.putExtra("judul", vholder.tv1.getText().toString());
                intent.putExtra("gambar", vholder.tv2.getText().toString());
                intent.putExtra("desk",desk[position]);

                context.startActivity(intent);



            Log.e(TAG,"kondisi bekerja");
        }
    };



    @Override
    public int getItemCount() {
        return name.length;
    }
}
