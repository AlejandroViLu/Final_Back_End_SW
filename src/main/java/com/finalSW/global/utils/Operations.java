package com.finalSW.global.utils;

import java.util.List;

import com.finalSW.global.entity.EntityId;

public class Operations {
	public static String arreglarMensaje(String message) {
		return message.replaceAll("[\\[\\]]", "");
	}
	public static int autoIncrement(List<? extends EntityId> list) {
		if(list.isEmpty()) {
			return 1;
		}else {
			return list.stream().max(java.util.Comparator.comparing(EntityId::getId)).get().getId()+1;
		}
	}
}
