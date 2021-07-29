package poly.edu.utils;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ObjectMapperUtils {
	
	@Autowired
    private  ModelMapper modelMapper;

    private ObjectMapperUtils() {
    }

   
    public  <D, T> D convertEntityAndDTO(final T entity, Class<D> outClass) {
        return modelMapper.map(entity, outClass);
    }


    public  <D, T> List<D> mapAll(final Collection<T> entityList, Class<D> outCLass) {
        return entityList.stream()
                .map(entity -> convertEntityAndDTO(entity, outCLass))
                .collect(Collectors.toList());
    }
    
   
   
}