package com.microbs.ui.main

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.microbs.R
import com.microbs.databinding.ActivityMainBinding
import com.microbs.ui.main.customers.CustomersFragment
import com.microbs.ui.main.employees.EmployeesFragment
import com.microbs.ui.main.retro.RetroFragment

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        var fragment: Fragment? = supportFragmentManager.findFragmentById(R.id.fragmentContainer)
        if (fragment == null) {
            fragment = EmployeesFragment.newInstance()
            supportFragmentManager.beginTransaction()
                .replace(R.id.fragmentContainer, fragment, EmployeesFragment.TAG)
                .commit()
        }

        binding.bottomNavigationView.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.employeesFragment -> {
                    val employeesFragment =
                        supportFragmentManager.findFragmentByTag(EmployeesFragment.TAG)
                            ?: EmployeesFragment.newInstance()
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.fragmentContainer, employeesFragment, EmployeesFragment.TAG)
                        .commit()
                }
                R.id.customersFragment -> {
                    val customersFragment =
                        supportFragmentManager.findFragmentByTag(CustomersFragment.TAG)
                            ?: CustomersFragment.newInstance()
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.fragmentContainer, customersFragment, CustomersFragment.TAG)
                        .commit()
                }
                R.id.retroFragment -> {
                    val retroFragment = supportFragmentManager.findFragmentByTag(RetroFragment.TAG)
                        ?: RetroFragment.newInstance()
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.fragmentContainer, retroFragment, RetroFragment.TAG)
                        .commit()
                }
            }
            true
        }
        binding.bottomNavigationView.setOnItemReselectedListener(null)


    }

    companion object {
        @JvmStatic
        fun start(context: Context) {
            val starter = Intent(context, MainActivity::class.java)
            context.startActivity(starter)
        }
    }
}