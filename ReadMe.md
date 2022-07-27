#PagingSource:
- It is a generic abstract class that is responsible for loading the paging data from the network. To implement PagingSource we need to define the Page Key type in our case it will be of type Int and the response data type from API in our case it will be DoggoImageModel.

#RemoteMediator: 
- It is responsible for loading the paging data from the network and local DB. This is a good way to implement paging since in this case, our local DB is the main source of data for the paging adapter. This method is much more reliable and less error-prone.

#Pager:
- This API consumes whatever the RemoteMediator or PagingSource returns as a data source to it and returns a reactive stream of PagingData. It can be returned as a Flow, Observable, LiveData as shown in the above diagram.

#PagingConfig: 
- This is our paging configuration class here you can define how the PagingSource should be constructed means you can define how much data should be there on each page and many more options are there to customize our PagingSource.

#PagingData: 
- This is the final return type and something that PagingDataAdapter understands and has the original data type inside it. It acts as a paging data container.


#PagingDataAdapter: 
- This is the primary UI component that is responsible for presenting the data in the RecyclerView. It consumes the PagingData as the input type and listens to its internal loading events. It loads data after fine graining using DiffUtil on a background thread, so expect no hiccups while adding new items on the UI thread.
