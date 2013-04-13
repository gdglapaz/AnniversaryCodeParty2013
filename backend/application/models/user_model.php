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
    	$response->user = new stdClass();
    	$response->user->data1 = "TEST1";
    	$response->user->data2= "TEST2";
    	$response->message = "OK";
    	return $response;
    }

    function salvarUsuario($params) {
    	$response = new stdClass();
    	$response->success = true;
    	$response->user = new stdClass();
    	$response->user->data1 = "TEST1";
    	$response->user->data2= "TEST2";
    	$response->message = "OK";
    	return $response;
    }
}