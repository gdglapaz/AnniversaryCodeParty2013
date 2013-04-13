<?php

class User_model extends CI_Model
{
    function __construct()
    {
        parent::__construct();
    }

    function listarUsuario($user_id) {
    	$response = new stdClass();
    	$response->success = true;
    	$response->user = $this->userFake();
    	$response->message = "OK";
    	return $response;
    }

    function salvarUsuario($params) {
    	$response = new stdClass();
    	$response->success = true;
    	$response->message = "Usuario Registrado Correctamente";
    	return $response;
    }

    function userFake() {
    	$data = '{
  "username":"alejandra.rojas@gmail.com",
  "first_name":"Alejandra",
  "last_name":"Rojas",
  "gender":"female",
  "country":{
    "code":"BOL",
    "pais":"Bolivia"
  },
  "depto":{
    "code":"LP",
    "departamento":"La Paz"
  },
  "contact_email":"alejandra.rojas@gmail.com",
  "mobile_number":"72254089",
  "badges":[
    {
      "type":"1",
      "gdg_membership":"developer"
    }
  ],
  "birth_date": "1985-01-20T00:00:00.000Z",
  "city":"La Paz",
  "industry":{
    "code":"4",
    "description":"Software"
  },
  "affiliation":"Colosa",
  "bio":"Soy una persona apasionada por los dispositivos móviles y la programación móvil",
  "identities":[
    {
      "network":"twitter",
      "preference":1,
      "profile":"alerojas"
    },
    {
      "network":"facebook",
      "preference":2,
      "profile":"alejandrarojas"
    },
    {
      "network":"google+",
      "preference":3,
      "profile":"alerojas"
    },
    {
      "network":"linkedin",
      "preference":4,
      "profile":"alejandra_rojas"
    },
    {
      "network":"skype",
      "preference":5,
      "profile":"arojas"
    }
  ],
  "years_experience":5,
  "occupations":[
    {
      "code":"emp",
      "description":"Empleado en startup"
    },
    {
      "code":"free",
      "description":"Profesional independiente"
    }
  ],
  "academic_level":[
    {
      "code":"lic",
      "description":"Licenciatura"
    }
  ],
  "website_url":"alejandrarojas.com",
  "current_careers":[
    {
      "code":"dev",
      "description":"Desarrollador"
    },
    {
      "code":"dsgn",
      "description":"Diseñador"
    }
  ],
  "languages":[
    {
      "code":"spa",
      "description":"Español"
    },
    {
      "code":"eng",
      "description":"Inglés"
    }
  ],
  "skills":[
    {
      "code":"php",
      "name":"PHP"
    },
    {
      "code":"ror",
      "name":"Ruby on Rails"
    }
  ],
  "education":[
    {
      "school":{
        "code":"UMSA",
        "name":"Universidad Mayor de San Andrés"
      },
      "field_study":"Ingeniería de Sistemas",
      "degree":{
        "code":"lic",
        "description":"Licenciatura"
      },
      "start_date":"2005-01-20T00:00:00.000Z",
      "end_date":"2010-01-20T00:00:00.000Z"
    }
  ],
  "positions":[
    {
      "company":{
        "code":"Colosa",
        "name":"Colosa SRL"
      },
      "title":"Desarrollador de aplicaciones móviles",
      "career":{
        "code":"dev",
        "description":"Desarrollador"
      },
      "start_date":"2011-01-20T00:00:00.000Z",
      "end_date":null
    }
  ],
  "desired_tech":[
    {
      "code":"cloud",
      "name":"cloud computing"
    },
    {
      "code":"other",
      "name":"cloud computing"
    }
  ],
  "desired_gtech":[
    {
      "code":"gcm",
      "description":"Google Cloud Messaging"
    },
    {
      "code":"other",
      "description":"New Google Tech"
    }
  ],
  "desired_careers":[
    {
      "code":"pm",
      "description":"Project Manager"
    },
    {
      "code":"manage",
      "description":"Gerencia"
    }
  ],
  "have_kids":false,
  "worklife_balance":[
    {
      "code":"cod1",
      "description":"Respuesta ya parametrizada"
    },
    {
      "code":"other",
      "description":"Nueva respuesta no parametrizada"
    }
  ],
  "pursue_career":[
    {
      "code":"cod1",
      "description":"Respuesta ya parametrizada"
    },
    {
      "code":"other",
      "description":"Nueva respuesta no parametrizada"
    }
  ],
  "greatest_challenge":[
    {
      "code":"cod1",
      "description":"Respuesta ya parametrizada"
    },
    {
      "code":"other",
      "description":"Nueva respuesta no parametrizada"
    }
  ],
  "stay_current":[
    {
      "code":"cod1",
      "description":"Respuesta ya parametrizada"
    },
    {
      "code":"other",
      "description":"Nueva respuesta no parametrizada"
    }
  ],
  "barriers_wit":[
    {
      "code":"cod1",
      "description":"Respuesta ya parametrizada"
    },
    {
      "code":"other",
      "description":"Nueva respuesta no parametrizada"
    }
  ],
  "bolivia_limits":[
    {
      "code":"cod1",
      "description":"Respuesta ya parametrizada"
    },
    {
      "code":"other",
      "description":"Nueva respuesta no parametrizada"
    }
  ],
  "training_interest":[
    {
      "code":"dev",
      "description":"Programación"
    },
    {
      "code":"grow",
      "description":"Desarrollo Personal"
    }
  ],
  "training_days":[
    {
      "day":"mon",
      "nameday":"Lunes"
    },
    {
      "day":"sat",
      "nameday":"Sábado"
    }
  ],
  "training_time":[
    {
      "code":"am",
      "description":"Por las mañanas"
    },
    {
      "code":"pm",
      "description":"Por las tardes"
    }
  ],
  "training_frequency":{
    "code":"una",
    "description":"Una vez a la semana"
   }
}';
    	return json_decode($data);

    }
}