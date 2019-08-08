package com.bhupesh.cricapi.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bhupesh.cricapi.domain.CricPlayInformation;
import com.bhupesh.cricapi.domain.CricPlayer;
import com.bhupesh.cricapi.repository.CricPlayInformationRepository;

@Service
public class CricPlayerInfoServiceImpl implements ICricPlayerInfoService {

	@Autowired
	CricPlayInformationRepository cricPlayInformationRepository;
	
	@Override
	public List<CricPlayer> getCricPlayerList() {
		// TODO Auto-generated method stub
		List<CricPlayInformation> cricPlayerList= cricPlayInformationRepository.findAll();
		List<CricPlayer> cpl = new ArrayList<CricPlayer>();
		
		CricPlayer cp = new CricPlayer();
		for(CricPlayInformation cricplayer:cricPlayerList) {
			System.out.println("cricPlayerList");
			cp = new CricPlayer();
			cp.setCricplayerid(cricplayer.getCricplayerid());
			cp.setName(cricplayer.getName());
			cp.setEmail(cricplayer.getEmail());
			cp.setScore(cricplayer.getScore());
			cpl.add(cp);
		}
		
		return cpl;
	}

	@Override
	public int saveCricPlayer(CricPlayer cricPlayer) {
		// TODO Auto-generated method stub
		CricPlayInformation cpi = new CricPlayInformation();
		
		cpi.setCricplayerid(cricPlayer.getCricplayerid());
		cpi.setEmail(cricPlayer.getEmail());
		cpi.setName(cricPlayer.getName());
		cpi.setScore(cricPlayer.getScore());
		CricPlayInformation cpi2= cricPlayInformationRepository.save(cpi);
		return cpi2.getScore();
	}

	@Override
	public int updateCricPlayer(CricPlayer cricPlayer) {
			
		CricPlayInformation cpi2= cricPlayInformationRepository.findByCricplayerid(cricPlayer.getCricplayerid());
		
		cpi2.setCricplayerid(cricPlayer.getCricplayerid());
		cpi2.setEmail(cricPlayer.getEmail());
		cpi2.setName(cricPlayer.getName());
		cpi2.setScore(cricPlayer.getScore());
		cricPlayInformationRepository.save(cpi2);
		
		return cpi2.getScore();
	}

	@Override
	public int deleteCricPlayer(CricPlayer cricPlayer) {
		// TODO Auto-generated method stub
	CricPlayInformation cpi2= cricPlayInformationRepository.findByCricplayerid(cricPlayer.getCricplayerid());
		
		cpi2.setCricplayerid(cricPlayer.getCricplayerid());
		cpi2.setEmail(cricPlayer.getEmail());
		cpi2.setName(cricPlayer.getName());
		cpi2.setScore(cricPlayer.getScore());
		
		cricPlayInformationRepository.delete(cpi2);
		return 1;
	}

}
