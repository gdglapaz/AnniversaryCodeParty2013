<?php
require APPPATH.'/libraries/rest/REST_Controller.php';
require APPPATH.'/libraries/gdgbo/gdgbo.php';

class Api extends REST_Controller
{
    private $gdgbo;

    public function __construct(){
        parent::__construct();
        header("Access-Control-Allow-Origin: *");
        $this->gdgbo = new GDGBo();
    }

    /**
     * USER Methods
     */
    function user_get($par = ''){
        $this->gdgbo->listar_usuario($par);
    }

    function user_post(){
        $this->gdgbo->salvar_usuario($this->_post_args);
    }

    function user_put($par = ''){
        $params = $this->_post_args;
        $params['id'] = $par;
        $this->gdgbo->salvar_usuario($params);
    }

    function user_options(){
        header ('Access-Control-Allow-Methods: POST, PUT, OPTIONS');
        header ('Access-Control-Allow-Headers: authorization, content-type');
        header ('Access-Control-Max-Age: 1728000');
        header ('Content-Length: 0');
        header ('Content-Type: text/plain');
    }
}