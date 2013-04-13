<?php
require APPPATH.'/libraries/rest/REST_Controller.php';

class Api extends REST_Controller
{
    public function __construct(){
        parent::__construct();
        header("Access-Control-Allow-Origin: *");
    }

    /**
     * USER Methods
     */
    function user_get($par = ''){
        echo "LISTAR";
    }

    function user_post(){
        echo "GRABAR";
    }

    function user_put($par = ''){
        echo "EDITAR";
    }

    function user_options(){
        header ('Access-Control-Allow-Methods: POST, PUT, OPTIONS');
        header ('Access-Control-Allow-Headers: authorization, content-type');
        header ('Access-Control-Max-Age: 1728000');
        header ('Content-Length: 0');
        header ('Content-Type: text/plain');
    }
}