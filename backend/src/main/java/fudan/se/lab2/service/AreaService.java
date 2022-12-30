package fudan.se.lab2.service;

import fudan.se.lab2.domain.Area;
import fudan.se.lab2.domain.Authority;

import fudan.se.lab2.domain.Book;
import fudan.se.lab2.domain.Category;
import fudan.se.lab2.repository.AreaRepository;
import fudan.se.lab2.repository.BookRepository;
import fudan.se.lab2.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class AreaService {
    @Autowired
    AreaRepository areaRepository;

    public Area get(int id) {
        Area a = areaRepository.findById(id).orElse(null);
        return a;
    }

    public Area getByName(String name){
        Area a = areaRepository.findAreaByName(name);
        return a;
    }

    //返回id-area键值对
    public String[] getArea(){
        Map<Integer,String> map = new HashMap<Integer, String>();
        List <Area> list = (List<Area>)areaRepository.findAll();
        String [] area = new String[list.size()];
        for(int i = 0 ; i < list.size(); i++){
            area[i] = list.get(i).getName();
            map.put(list.get(i).getId(),list.get(i).getName());
        }
        return area;
    }

    public int getAid(String area){
        Area a = areaRepository.findAreaByName(area);
        return a.getId();
     }

}
