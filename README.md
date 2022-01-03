Currency List Exercise
==
**Architecture**
1. Implement business logic in ViewModel.
2. Get data from single entry point: `CurrencyRepository`.

**Requirement**
1. CurrencyListFragment should receive an array list of CurrencyInfo to create the ui.
   - Use `CurrencyListMediatorLiveData` to present array list to observer in CurrencyListFragment.
2. DemoActivity should provide 1 dataset, Currency List A of CurrencyInfo to
   CurrencyListFragment and the dataset should be queried from local db
   - Use room insert/query to fulfill the requirement.
   - Load data into database when database created using a callback in CurrencyRepository.
3. DemoActivity should provide 2 buttons to do the demo.
   - Load data and display: Query list from room into LiveData.
   - Sorting: Toggle sort order LiveData, which is a source of `CurrencyListMediatorLiveData`.
4. CurrencyListFragment should provide a hook of item click listener to the parent.
   - Set a callback to CurrencyListAdapter. Bind to callback to view holder in `onBindViewHolder`.
5. Use two LiveData and add them as the sources of `CurrencyListMediatorLiveData`.
   The final result will always calculated with final value of `_currencyList` and `_order`
6. Unit tests.
   - `currencyListMediatorLiveData_isCorrect`: Check the behavior of custom LiveData works fine.
   - `dataConversion_isCorrect`: Check the behavior of data conversion & data class is correct.
7. Use ktlint to check code format.