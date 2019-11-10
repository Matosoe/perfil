package br.com.perfilcar.perfil.resources;

import java.time.ZonedDateTime;
import java.util.List;
import java.util.Properties;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import br.com.perfilcar.perfil.models.Perfil;
import br.com.perfilcar.perfil.repository.PerfilRepository;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping(path = "/perfil")
@Api(value = "API REST Perfis")
@CrossOrigin(origins="*")
public class PerfilResource {
	
	String topicName = "PerfilCarLog";
	String key = "Usuario";
	
	@Autowired
	PerfilRepository perfilRepository;
	
	
	@GetMapping("/perfis")
	@ApiOperation(value="Retorna uma lista de perfis")
	public List<Perfil> listaPerfis(){
		  String value = "Pedido de Lista de perfil" + "-" + ZonedDateTime.now().toString();
	      
	      Properties props = new Properties();
	      props.put("bootstrap.servers", "localhost:9092,localhost:9093");
	      props.put("key.serializer","org.apache.kafka.common.serialization.StringSerializer");         
	      props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");
		        
	      Producer<String, String> producer = new KafkaProducer <>(props);
		
		  ProducerRecord<String, String> record = new ProducerRecord<>(topicName,key,value);
		  producer.send(record);	       
	      producer.close();
	
		return perfilRepository.findAll();
	}
	
	@GetMapping("/perfis/{emailProprietario}")
	@ApiOperation(value="Retorna uma lista de perfis do e-mail especificado")
	public List<Perfil> listaPerfisPorEmail(@PathVariable(value = "emailProprietario") String emailProprietario){
		  String value = "Pedido de Lista de perfil por e-mail" + "-" + emailProprietario + "-" + ZonedDateTime.now().toString();
	      
	      Properties props = new Properties();
	      props.put("bootstrap.servers", "localhost:9092,localhost:9093");
	      props.put("key.serializer","org.apache.kafka.common.serialization.StringSerializer");         
	      props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");
		        
	      Producer<String, String> producer = new KafkaProducer <>(props);
		
		  ProducerRecord<String, String> record = new ProducerRecord<>(topicName,key,value);
		  producer.send(record);	       
	      producer.close();

		return perfilRepository.findByEmailProprietario(emailProprietario);
	}
	
	@GetMapping("/perfil/{id}")
	@ApiOperation(value="Retorna um perfil unico do Id especificado")
	public Perfil listaPerfilUnico(@PathVariable(value = "id") long id){
		  String value = "Pedido de Lista de perfil por ID" + "-" + id + "-" + ZonedDateTime.now().toString();
	      
	      Properties props = new Properties();
	      props.put("bootstrap.servers", "localhost:9092,localhost:9093");
	      props.put("key.serializer","org.apache.kafka.common.serialization.StringSerializer");         
	      props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");
		        
	      Producer<String, String> producer = new KafkaProducer <>(props);
		
		  ProducerRecord<String, String> record = new ProducerRecord<>(topicName,key,value);
		  producer.send(record);	       
	      producer.close();

		return perfilRepository.findById(id);
	}
	
	@PostMapping("/perfil")
	@ApiOperation(value="Cria um perpil com os dados informados")
	public Perfil salvaPerfil(@RequestBody Perfil perfil) {
		  String value = "Pedido de criacao de Perfil" + "-" + ZonedDateTime.now().toString();
	      
	      Properties props = new Properties();
	      props.put("bootstrap.servers", "localhost:9092,localhost:9093");
	      props.put("key.serializer","org.apache.kafka.common.serialization.StringSerializer");         
	      props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");
		        
	      Producer<String, String> producer = new KafkaProducer <>(props);
		
		  ProducerRecord<String, String> record = new ProducerRecord<>(topicName,key,value);
		  producer.send(record);	       
	      producer.close();

		return perfilRepository.save(perfil);
	}

	@PutMapping("/perfil")
	@ApiOperation(value="Atualiza um perfil com os dados informados")
	public Perfil atualizaPerfil(@RequestBody Perfil perfil) {
		  String value = "Pedido de atualizacao de perfil" + "-" + ZonedDateTime.now().toString();
	      
	      Properties props = new Properties();
	      props.put("bootstrap.servers", "localhost:9092,localhost:9093");
	      props.put("key.serializer","org.apache.kafka.common.serialization.StringSerializer");         
	      props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");
		        
	      Producer<String, String> producer = new KafkaProducer <>(props);
		
		  ProducerRecord<String, String> record = new ProducerRecord<>(topicName,key,value);
		  producer.send(record);	       
	      producer.close();
		return perfilRepository.save(perfil);
	}

	@DeleteMapping("/perfil")
	@ApiOperation(value="Deleta o perfil Informado")
	 public void deletaPerfil(@RequestBody Perfil perfil) {
		  String value = "Pedido de delecao de perfil" + "-" + ZonedDateTime.now().toString();
	      
	      Properties props = new Properties();
	      props.put("bootstrap.servers", "localhost:9092,localhost:9093");
	      props.put("key.serializer","org.apache.kafka.common.serialization.StringSerializer");         
	      props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");
		        
	      Producer<String, String> producer = new KafkaProducer <>(props);
		
		  ProducerRecord<String, String> record = new ProducerRecord<>(topicName,key,value);
		  producer.send(record);	       
	      producer.close();

		perfilRepository.delete(perfil);
	}
}