package com.github.il4enkodev.househeating.presentation.ui.fragment.readings

import android.annotation.SuppressLint
import android.app.DatePickerDialog
import android.app.Dialog
import android.app.TimePickerDialog
import android.os.Bundle
import android.text.InputFilter
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.DatePicker
import android.widget.TimePicker
import androidx.fragment.app.viewModels
import com.github.il4enkodev.househeating.R
import com.github.il4enkodev.househeating.databinding.ReadingsEditDialogBinding
import com.github.il4enkodev.househeating.presentation.di.qualifier.ReadingFilters
import com.github.il4enkodev.househeating.presentation.ui.events.ViewRequest
import com.github.il4enkodev.househeating.presentation.ui.events.ViewResult
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import dagger.hilt.android.AndroidEntryPoint
import java.time.LocalDate
import java.time.LocalTime
import java.time.Month
import javax.inject.Inject

@AndroidEntryPoint
class ReadingsEditDialog: BottomSheetDialogFragment(),
        DatePickerDialog.OnDateSetListener, TimePickerDialog.OnTimeSetListener {

    @Inject @ReadingFilters
    lateinit var filters: Array<InputFilter>
    private val vm: ReadingsEditViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val readings = ReadingsEditDialogArgs.fromBundle(requireArguments()).reading
        vm.initialize(readings)
    }

    override fun onCreateView(inflater: LayoutInflater,
                              container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        val binding = ReadingsEditDialogBinding.inflate(inflater, container, false)
        binding.viewModel = vm
        binding.lifecycleOwner = viewLifecycleOwner
        binding.etReadings.filters = filters
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        vm.result().observe(viewLifecycleOwner) { result ->
            val bundle = Bundle().apply { putParcelable(result.key, result.content) }
            parentFragmentManager.setFragmentResult(result.key, bundle)
            dismiss()
        }

        vm.requests().observe(viewLifecycleOwner) { request ->
            when (request) {
                is ViewRequest.EditDate -> openDateEditDialog(request.content)
                is ViewRequest.EditTime -> openTimeEditDialog(request.content)
                else -> Unit
            }
        }
    }

    @SuppressLint("RestrictedApi", "VisibleForTests")
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val dialog = super.onCreateDialog(savedInstanceState) as BottomSheetDialog
        dialog.setOnShowListener {
            dialog.behavior.disableShapeAnimations()
            dialog.behavior.state = BottomSheetBehavior.STATE_EXPANDED
        }
        return dialog
    }

    override fun onDateSet(view: DatePicker?, year: Int, month: Int, dayOfMonth: Int) {
        val date = LocalDate.of(year, Month.values()[month], dayOfMonth)
        vm.onViewResult(ViewResult.DateUpdated(date))
    }

    override fun onTimeSet(view: TimePicker?, hourOfDay: Int, minute: Int) {
        val time = LocalTime.of(hourOfDay, minute)
        vm.onViewResult(ViewResult.TimeUpdated(time))
    }

    private fun openTimeEditDialog(time: LocalTime) {
        TimePickerDialog(
                requireContext(),
                R.style.ThemeOverlay_App_Dialog_Alert_TimePicker,
                this,
                time.hour,
                time.minute,
                true
        ).show()
    }

    private fun openDateEditDialog(date: LocalDate) {
        DatePickerDialog(
                requireContext(),
                R.style.ThemeOverlay_App_Dialog_Alert_DatePicker,
                this,
                date.year,
                date.month.ordinal, // required 0..11
                date.dayOfMonth
        ).show()
    }
}