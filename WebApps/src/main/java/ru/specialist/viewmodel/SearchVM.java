package ru.specialist.viewmodel;

public class SearchVM {
	
	private String search = "";

	// search
	public String getSearch() {
		return search == null ? "" : search;
	}
	public void setSearch(String search) {
		this.search = search;
	}
	
	// searchString
	public String getSearchString()
	{
		return getSearch()==null ? "%" : "%"+getSearch().trim()+"%";
	}

}
