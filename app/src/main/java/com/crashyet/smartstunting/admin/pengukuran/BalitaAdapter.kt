package com.crashyet.smartstunting.admin.pengukuran

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.recyclerview.widget.RecyclerView
import androidx.transition.AutoTransition
import androidx.transition.TransitionManager
import com.crashyet.smartstunting.R

//class BalitaAdapter(
//    private val listBalita: List<BalitaModel>
//) : RecyclerView.Adapter<BalitaAdapter.BalitaViewHolder>() {

//    inner class BalitaViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
//        val tvNama: TextView = itemView.findViewById(R.id.tvNama)
//        val tvTanggalUmur: TextView = itemView.findViewById(R.id.tvTanggalUmur)
//        val tvGender: TextView = itemView.findViewById(R.id.tvGender)
//        val detailContainer: LinearLayout = itemView.findViewById(R.id.detailContainer)
//        val container: LinearLayout = itemView.findViewById(R.id.containerBalita)
//        val btnLihat: Button = itemView.findViewById(R.id.btnLihat)
//        val btnHapus: Button = itemView.findViewById(R.id.btnHapus)
//    }
//
//    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BalitaViewHolder {
//        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_balita, parent, false)
//        return BalitaViewHolder(view)
//    }
//
//    override fun onBindViewHolder(holder: BalitaViewHolder, position: Int) {
//        val data = listBalita[position]
//
//        holder.tvNama.text = data.nama
//        holder.tvTanggalUmur.text = "${data.tanggalLahir} • ${data.umurBulan} bln"
//        holder.tvGender.text = data.gender
//
//        // Warna background gender
//        if (data.gender.lowercase() == "laki-laki") {
//            holder.tvGender.setBackgroundResource(R.drawable.bg_gender_laki)
//        } else {
//            holder.tvGender.setBackgroundResource(R.drawable.bg_gender_perempuan)
//        }
//
//        // Awalnya detail disembunyikan
//        holder.detailContainer.visibility = View.GONE
//
//        // Toggle animasi expand/collapse
//        holder.container.setOnClickListener {
//            if (holder.detailContainer.visibility == View.GONE) {
//                TransitionManager.beginDelayedTransition(holder.container, AutoTransition())
//                holder.detailContainer.visibility = View.VISIBLE
//            } else {
//                TransitionManager.beginDelayedTransition(holder.container, AutoTransition())
//                holder.detailContainer.visibility = View.GONE
//            }
//        }
//
//        holder.btnLihat.setOnClickListener {
//            Toast.makeText(holder.itemView.context, "Lihat ${data.nama}", Toast.LENGTH_SHORT).show()
//        }
//
//        holder.btnHapus.setOnClickListener {
//            Toast.makeText(holder.itemView.context, "Hapus ${data.nama}", Toast.LENGTH_SHORT).show()
//        }
//    }

//    override fun getItemCount(): Int = listBalita.size
//}
