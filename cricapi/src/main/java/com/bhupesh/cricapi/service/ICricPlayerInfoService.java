package com.bhupesh.cricapi.service;

import java.util.List;

import com.bhupesh.cricapi.domain.CricPlayer;

public interface ICricPlayerInfoService {

	public List<CricPlayer> getCricPlayerList();
	public int saveCricPlayer(CricPlayer cricPlayer);
	public int updateCricPlayer(CricPlayer cricPlayer);
	public int deleteCricPlayer(CricPlayer cricPlayer);
	
}
