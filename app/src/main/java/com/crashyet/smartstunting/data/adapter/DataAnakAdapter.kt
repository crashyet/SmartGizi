package com.crashyet.smartstunting.data.adapter

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.crashyet.smartstunting.data.model.DataAnakModel
import com.crashyet.smartstunting.R

class DataAnakAdapter(private val listAnak: List<DataAnakModel>) :
    RecyclerView.Adapter<DataAnakAdapter.ViewHolder>()  {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val nama: TextView = view.findViewById(R.id.tvNamaAnak)
        val umur: TextView = view.findViewById(R.id.tvUmur)
        val jenisKelamin: TextView = view.findViewById(R.id.tvJenisKelamin)
        val bgJenisKelamin: View = view.findViewById(R.id.bgJenisKelamin)
        val nik: TextView = view.findViewById(R.id.tvNIK)
        val posyandu: TextView = view.findViewById(R.id.tvPosyandu)
        val namaAyah: TextView = view.findViewById(R.id.tvNamaAyah)
        val status: TextView = view.findViewById(R.id.tvStatus)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.items_data_anak, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val anak = listAnak[position]
        holder.nama.text = anak.nama
        holder.umur.text = anak.umur
        holder.jenisKelamin.text = anak.jenis_kelamin
        holder.nik.text = anak.nik
        holder.posyandu.text = anak.posyandu
        holder.namaAyah.text = anak.nama_ayah
        holder.status.text = anak.status

        // 🔹 Warna berdasarkan jenis kelamin
        when (anak.jenis_kelamin.lowercase()) {
            "laki-laki" -> {
                holder.jenisKelamin.setTextColor(Color.parseColor("#4577F6"))
                holder.bgJenisKelamin.setBackgroundResource(R.drawable.bg_gender_laki)
            }
            "perempuan" -> {
                holder.jenisKelamin.setTextColor(Color.parseColor("#E91E63"))
                holder.bgJenisKelamin.setBackgroundResource(R.drawable.bg_gender_perempuan)
            }
            else -> {
                holder.jenisKelamin.setTextColor(Color.GRAY)
                holder.bgJenisKelamin.setBackgroundResource(0)
            }
        }

        when (anak.status.lowercase()) {
            "aktif" -> {
                holder.status.setTextColor(Color.parseColor("#2FA45A"))
                holder.status.setBackgroundResource(R.drawable.aktif9900)
            }
            "non-aktif", "non aktif" -> {
                holder.status.setTextColor(Color.parseColor("#FF0000"))
                holder.status.setBackgroundResource(R.drawable.nonaktif9900)
            }
            else -> {
                holder.status.setTextColor(Color.GRAY)
                holder.status.setBackgroundResource(0) // hapus background
            }
        }

    }

    override fun getItemCount() = listAnak.size
}