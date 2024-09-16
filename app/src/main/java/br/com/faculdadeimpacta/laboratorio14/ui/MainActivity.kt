package br.com.faculdadeimpacta.laboratorio14.ui

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.CheckBox
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import br.com.faculdadeimpacta.laboratorio14.R
import br.com.faculdadeimpacta.laboratorio14.data.Tarefa
import br.com.faculdadeimpacta.laboratorio14.databinding.ActivityMainBinding
import br.com.faculdadeimpacta.laboratorio14.databinding.TarefaItemBinding

class MainActivity : AppCompatActivity() {

    private var _binding: ActivityMainBinding? = null
    private val binding get() = _binding!!
    private val listaDeTarefas: MutableList<Tarefa> = mutableListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(binding.main) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    override fun onStart() {
        super.onStart()

        listaDeTarefas.add(Tarefa("Lavar o carro", "Levar no lava-rápido", 3, false))
        listaDeTarefas.add(
            Tarefa(
                "Estudar programação",
                "aprender a linguagem de kotlin",
                1,
                false
            )
        )
        listaDeTarefas.add(Tarefa("Ir ao mercado", "Comprar: leite e mistura", 2, false))
        listaDeTarefas.add(Tarefa("Tomar banho", "ficar limpinho", 4, false))
        listaDeTarefas.add(
            Tarefa(
                "Aperfeiçoar a programação em Kotlin",
                "praticar mais o kotlin",
                5,
                false
            )
        )

        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        binding.recyclerView.adapter =
            TarefaAdaptador(listaDeTarefas.sortedWith { a, b -> a.prioridade - b.prioridade })
    }

    class TarefaAdaptador(private val listaDeTarefas: List<Tarefa>) :
        RecyclerView.Adapter<TarefaAdaptador.TarefaVH>() {

        inner class TarefaVH(val binding: TarefaItemBinding) : RecyclerView.ViewHolder(binding.root)

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TarefaVH {
            val layoutInflater = LayoutInflater.from(parent.context)
            val binding = TarefaItemBinding.inflate(layoutInflater, parent, false)
            return TarefaVH(binding)
        }

        override fun onBindViewHolder(holder: TarefaVH, position: Int) {
            holder.binding.tarefa = listaDeTarefas[position]
            holder.binding.checkbox.setOnClickListener { view ->
                listaDeTarefas[position].concluido = (view as CheckBox).isChecked
                Log.i(
                    "TarefaAdaptador",
                    "listaDeTarefas[${position}].concluido = ${listaDeTarefas[position].concluido}"
                )
            }
        }

        override fun getItemCount(): Int = listaDeTarefas.size
    }
}