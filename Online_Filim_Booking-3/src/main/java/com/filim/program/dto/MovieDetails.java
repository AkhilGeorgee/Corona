package com.filim.program.dto;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Movie_Details_Table")
public class MovieDetails 
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "movie_id")
     private long mId;
	
	@Column(name = "movie_name",nullable = false)
     private String movieName;
	
	@Column(name = "movie_details",columnDefinition = "varchar(3000) ", nullable = false)
     private String mDetails;
	
     @Column(nullable = false)
     private String image;

	public long getmId() {
		return mId;
	}

	public void setmId(long mId) {
		this.mId = mId;
	}

	public String getMovieName() {
		return movieName;
	}

	public void setMovieName(String movieName) {
		this.movieName = movieName;
	}

	public String getmDetails() {
		return mDetails;
	}

	public void setmDetails(String mDetails) {
		this.mDetails = mDetails;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	
	public MovieDetails() 
	{
		super();
		System.out.println(this.getClass().getSimpleName()+"Object Created");
	}

	public MovieDetails(long mId, String movieName, String mDetails, String image) {
		super();
		this.mId = mId;
		this.movieName = movieName;
		this.mDetails = mDetails;
		this.image = image;
	}

	public MovieDetails(String movieName, String mDetails, String image) {
		super();
		this.movieName = movieName;
		this.mDetails = mDetails;
		this.image = image;
	}

	@Override
	public String toString() {
		return "MovieDetails [mId=" + mId + ", movieName=" + movieName + ", mDetails=" + mDetails + ", image=" + image
				+ "]";
	}
	
	

	
     
     
}
