//
//  ViewController.m
//  SineDoloreSVM
//
//  Created by Aitor Costa Jiménez on 16/04/14.
//  Copyright (c) 2014 SineDolore. All rights reserved.
//

#import "ViewController.h"

@interface ViewController ()

@end

//definimos el punto medio de la vista
#define latit 39.471914;
#define longi -0.376797;

//Span
#define spanvar 0.01f;

@implementation ViewController;
@synthesize MV;

- (void)viewDidLoad
{
    [super viewDidLoad];
    //Create the Region
    MKCoordinateRegion myregion;
    
    // center
    CLLocationCoordinate2D center;
    center.latitude = latit;
    center.longitude= longi;

    //span
    MKCoordinateSpan span;
    span.latitudeDelta = spanvar;
    span.longitudeDelta = spanvar;
    
    myregion.center=center;
    myregion.span = span;
    
    //set our mapview
    [MV setRegion: myregion animated:YES];
    
    
    //Pin1
    CLLocationCoordinate2D pin1;
    pin1.latitude = 39.881478;
    pin1.longitude = 4.252449;
    MKPointAnnotation *annotationPoint1 = [[MKPointAnnotation alloc] init];
    annotationPoint1.coordinate = pin1;
    annotationPoint1.title = @"Hospital General Mateu Orfila";
    annotationPoint1.subtitle = @"Dr. Jordi Moya Riera";
    [Localizacion addAnnotation:annotationPoint1];
    
    //Pin2
    CLLocationCoordinate2D pin2;
    pin2.latitude = 39.8854506;
    pin2.longitude = 4.2575863;
    MKPointAnnotation *annotationPoint2 = [[MKPointAnnotation alloc] init];
    annotationPoint2.coordinate = pin2;
    annotationPoint2.title = @"Instituto ClÌnico del Dolor";
    annotationPoint2.subtitle = @"Dr. Jordi Moya Riera";
    [Localizacion addAnnotation:annotationPoint2];
    
    //Pin3
    CLLocationCoordinate2D pin3;
    pin3.latitude = 39.8854506;
    pin3.longitude = 4.2575863;
    MKPointAnnotation *annotationPoint3 = [[MKPointAnnotation alloc] init];
    annotationPoint3.coordinate = pin3;
    annotationPoint3.title = @"Policlínica Virgen de Gracia";
    annotationPoint3.subtitle = @"Dr. Manuel Corral Rosado";
    [Localizacion addAnnotation:annotationPoint3];
    
    //Pin  4
    CLLocationCoordinate2D pin4;
    pin4.latitude = 28.470986;
    pin4.longitude =  -16.266733;
    MKPointAnnotation *annotationPoint4 = [[MKPointAnnotation alloc] init];
    annotationPoint4.coordinate = pin4;
    annotationPoint4.title = @"USP Hospital La Colina";
    annotationPoint4.subtitle = @"Dr. L. Javier Santos Yglesias";
    [Localizacion addAnnotation:annotationPoint4];
    
    //Pin5
    CLLocationCoordinate2D pin5;
    pin5.latitude = 39.606978;
    pin5.longitude = 2.646117;
    MKPointAnnotation *annotationPoint5 = [[MKPointAnnotation alloc] init];
    annotationPoint5.coordinate = pin5;
    annotationPoint5.title = @"USP Hospital La Colina";
    annotationPoint5.subtitle = @"Dr. Alfonso Vidal Marcos";
    [Localizacion addAnnotation:annotationPoint5];
    
    //Pin6
    CLLocationCoordinate2D pin6;
    pin6.latitude = 40.409329;
    pin6.longitude = -3.8623285;
    MKPointAnnotation *annotationPoint6 = [[MKPointAnnotation alloc] init];
    annotationPoint6.coordinate = pin6;
    annotationPoint6.title = @"Hospital Madrid MonteprÌncipe";
    annotationPoint6.subtitle = @"Dr. JosÈ M™ Hern·ndez GarcÌa";
    [Localizacion addAnnotation:annotationPoint6];
    
    //Pin7
    CLLocationCoordinate2D pin7;
    pin7.latitude = 40.4496858;
    pin7.longitude =  -3.6520703;
    MKPointAnnotation *annotationPoint7 = [[MKPointAnnotation alloc] init];
    annotationPoint7.coordinate = pin7;
    annotationPoint7.title = @"Hospital Nuestra SeÒora de AmÈrica";
    annotationPoint7.subtitle = @"Dr. JosÈ M™ Hern·ndez GarcÌa";
    [Localizacion addAnnotation:annotationPoint7];
    
    //Pin8
    CLLocationCoordinate2D pin8;
    pin8.latitude = 38.358907;
    pin8.longitude = -0.357549;
    MKPointAnnotation *annotationPoint8 = [[MKPointAnnotation alloc] init];
    annotationPoint8.coordinate = pin8;
    annotationPoint8.title = @"Hospital Internacional Medimar";
    annotationPoint8.subtitle = @"Dr. JosÈ M. RamÛn PÈrez";
    [Localizacion addAnnotation:annotationPoint8];
    
    //Pin9
    CLLocationCoordinate2D pin9;
    pin9.latitude = 38.35274;
    pin9.longitude = -0.476365;
    MKPointAnnotation *annotationPoint9 = [[MKPointAnnotation alloc] init];
    annotationPoint9.coordinate = pin9;
    annotationPoint9.title = @"Hospital Perpetuo Socorro";
    annotationPoint9.subtitle = @"Dr. JosÈ M. RamÛn PÈrez";
    [Localizacion addAnnotation:annotationPoint9];
    
    //Pin10
    CLLocationCoordinate2D pin10;
    pin10.latitude = 41.406374;
    pin10.longitude = 2.127423;
    MKPointAnnotation *annotationPoint10 = [[MKPointAnnotation alloc] init];
    annotationPoint10.coordinate = pin10;
    annotationPoint10.title = @"ClÌnica del Dolor Teknon";
    annotationPoint10.subtitle = @"Dr. Luis Aliaga Font";
    [Localizacion addAnnotation:annotationPoint10];
    
    //Pin11
    CLLocationCoordinate2D pin11;
    pin11.latitude = 41.403976;
    pin11.longitude = 2.141958;
    MKPointAnnotation *annotationPoint11 = [[MKPointAnnotation alloc] init];
    annotationPoint11.coordinate = pin11;
    annotationPoint11.title = @"AvantmËdic: Unidad de tratamiento del Dolor";
    annotationPoint11.subtitle = @"Dr. Jordi Guitart Vela";
    [Localizacion addAnnotation:annotationPoint11];
    
    //Pin12
    CLLocationCoordinate2D pin12;
    pin12.latitude = 43.268507;
    pin12.longitude = -2.946698;
    MKPointAnnotation *annotationPoint12 = [[MKPointAnnotation alloc] init];
    annotationPoint12.coordinate = pin12;
    annotationPoint12.title = @"ClÌnica del Dolor Praxis Bilbao";
    annotationPoint12.subtitle = @"Dra. M™ Luisa Franco Gay";
    [Localizacion addAnnotation:annotationPoint12];
    
    //Pin13
    CLLocationCoordinate2D pin13;
    pin13.latitude = 42.344065;
    pin13.longitude = -3.683322;
    MKPointAnnotation *annotationPoint13 = [[MKPointAnnotation alloc] init];
    annotationPoint13.coordinate = pin13;
    annotationPoint13.title = @"Hospital Recoletas";
    annotationPoint13.subtitle = @"Dr. Pedro Olmos Leza˙n y Dr. Emilio Jes˙s GarcÌa Camarero";
    [Localizacion addAnnotation:annotationPoint13];
    
    //Pin14
    CLLocationCoordinate2D pin14;
    pin14.latitude =41.979602 ;
    pin14.longitude = 2.82067;
    MKPointAnnotation *annotationPoint14 = [[MKPointAnnotation alloc] init];
    annotationPoint14.coordinate = pin14;
    annotationPoint14.title = @"ClÌnica Bofill";
    annotationPoint14.subtitle = @"Dr. Josep Vilaplana";
    [Localizacion addAnnotation:annotationPoint14];
    
    //Pin15
    CLLocationCoordinate2D pin15;
    pin15.latitude = 39.606978;
    pin15.longitude = 2.646117;
    MKPointAnnotation *annotationPoint15 = [[MKPointAnnotation alloc] init];
    annotationPoint15.coordinate = pin15;
    annotationPoint15.title = @"Hospital Ntra. Sra. de Regla";
    annotationPoint15.subtitle = @"Dr. Jorge LuÌs RodrÌguez Gonz·lez";
    [Localizacion addAnnotation:annotationPoint15];
    
    //Pin16
    CLLocationCoordinate2D pin16;
    pin16.latitude = 42.590259;
    pin16.longitude = -5.572257;
    MKPointAnnotation *annotationPoint16 = [[MKPointAnnotation alloc] init];
    annotationPoint16.coordinate = pin16;
    annotationPoint16.title = @"ClÌnica San Francisco de LeÛn ";
    annotationPoint16.subtitle = @"Dr. Jorge LuÌs RodrÌguez Gonz·lez";
    [Localizacion addAnnotation:annotationPoint16];
    
    //Pin17
    CLLocationCoordinate2D pin17;
    pin17.latitude = 42.590259;
    pin17.longitude = -5.572257;
    MKPointAnnotation *annotationPoint17 = [[MKPointAnnotation alloc] init];
    annotationPoint17.coordinate = pin17;
    annotationPoint17.title = @"AvantmËdic: Unitat de tractament del Dolor";
    annotationPoint17.subtitle = @"Dra. Maria MarcË Matute Crespo y Dr. Antonio Montero Matamala";
    [Localizacion addAnnotation:annotationPoint17];
    
    //Pin18
    CLLocationCoordinate2D pin18;
    pin18.latitude = 42.34155;
    pin18.longitude = -7.86118;
    MKPointAnnotation *annotationPoint18 = [[MKPointAnnotation alloc] init];
    annotationPoint18.coordinate = pin18;
    annotationPoint18.title = @"Centro MÈdico El Carmen";
    annotationPoint18.subtitle = @" Dr. Marcos Casto Bande y Dra. Luz C·novas MartÌnez";
    [Localizacion addAnnotation:annotationPoint18];
    
    //Pin19
    CLLocationCoordinate2D pin19;
    pin19.latitude = 43.361727;
    pin19.longitude = -5.861215;
    MKPointAnnotation *annotationPoint19 = [[MKPointAnnotation alloc] init];
    annotationPoint19.coordinate = pin19;
    annotationPoint19.title = @"ClÌnica del Dolor";
    annotationPoint19.subtitle = @"Dra. Maria Jes˙s RodrÌguez DintÈn";
    [Localizacion addAnnotation:annotationPoint19];
    
    //Pin20
    CLLocationCoordinate2D pin20;
    pin20.latitude = 39.5799161;
    pin20.longitude = 2.6481625;
    MKPointAnnotation *annotationPoint20 = [[MKPointAnnotation alloc] init];
    annotationPoint20.coordinate = pin20;
    annotationPoint20.title = @"Instituto Balear del Dolor";
    annotationPoint20.subtitle = @"Dr. Marcello G. Meli";
    [Localizacion addAnnotation:annotationPoint20];
    
    //Pin21
    CLLocationCoordinate2D pin21;
    pin21.latitude = 39.5787139;
    pin21.longitude = 2.6525172;
    MKPointAnnotation *annotationPoint21 = [[MKPointAnnotation alloc] init];
    annotationPoint21.coordinate = pin21;
    annotationPoint21.title = @"Instituto Balear del Dolor";
    annotationPoint21.subtitle = @"Dr. Ignacio GarcÌa Praderas";
    [Localizacion addAnnotation:annotationPoint21];
    
    //Pin22
    CLLocationCoordinate2D pin22;
    pin22.latitude = 41.3988142;
    pin22.longitude = 2.1693714;
    MKPointAnnotation *annotationPoint22 = [[MKPointAnnotation alloc] init];
    annotationPoint22.coordinate = pin22;
    annotationPoint22.title = @"Centre MËdic Verdaguer";
    annotationPoint22.subtitle = @"Dr. Jose Luis De CÛrdoba Benedicto";
    [Localizacion addAnnotation:annotationPoint22];
    
    //Pin23
    CLLocationCoordinate2D pin23;
    pin23.latitude =41.412178 ;
    pin23.longitude = 2.13589;
    MKPointAnnotation *annotationPoint23 = [[MKPointAnnotation alloc] init];
    annotationPoint23.coordinate = pin23;
    annotationPoint23.title = @"ClÌnica Sant Honorat";
    annotationPoint23.subtitle = @"Dr. Jose Luis De CÛrdoba Benedicto";
    [Localizacion addAnnotation:annotationPoint23];
    
    //Pin24
    CLLocationCoordinate2D pin24;
    pin24.latitude =41.544633 ;
    pin24.longitude = 2.107551;
    MKPointAnnotation *annotationPoint24 = [[MKPointAnnotation alloc] init];
    annotationPoint24.coordinate = pin24;
    annotationPoint24.title = @"Dolor Salut";
    annotationPoint24.subtitle = @"Dr. Jose Luis De CÛrdoba Benedicto";
    [Localizacion addAnnotation:annotationPoint24];
    
    //Pin25
    CLLocationCoordinate2D pin25;
    pin25.latitude = 43.31845;
    pin25.longitude = -1.958078;
    MKPointAnnotation *annotationPoint25 = [[MKPointAnnotation alloc] init];
    annotationPoint25.coordinate = pin25;
    annotationPoint25.title = @"Donostia Dolor";
    annotationPoint25.subtitle = @"Dr. Fermín Haro Sanz";
    [Localizacion addAnnotation:annotationPoint25];
    
    //Pin26
    CLLocationCoordinate2D pin26;
    pin26.latitude =43.46351 ;
    pin26.longitude = -3.8036015;
    MKPointAnnotation *annotationPoint26 = [[MKPointAnnotation alloc] init];
    annotationPoint26.coordinate = pin26;
    annotationPoint26.title = @"ClÌnica del Dolor";
    annotationPoint26.subtitle = @"Dr. J.M. Carceller Malo";
    [Localizacion addAnnotation:annotationPoint26];
    
    //Pin27
    CLLocationCoordinate2D pin27;
    pin27.latitude = 37.3635079;
    pin27.longitude = -5.9843385;
    MKPointAnnotation *annotationPoint27 = [[MKPointAnnotation alloc] init];
    annotationPoint27.coordinate = pin27;
    annotationPoint27.title = @"Centro de CirugÌa Mayor Ambulatoria Ave MarÌa";
    annotationPoint27.subtitle = @"Dr. Miguel ¡ngel Merino MÈndez";
    [Localizacion addAnnotation:annotationPoint27];
    
    //Pin28
    CLLocationCoordinate2D pin28;
    pin28.latitude = 37.369044;
    pin28.longitude = -5.987381;
    MKPointAnnotation *annotationPoint28 = [[MKPointAnnotation alloc] init];
    annotationPoint28.coordinate = pin28;
    annotationPoint28.title = @"ClÌnica PiÒeiro";
    annotationPoint28.subtitle = @"Dr. Domingo Ventura Vargas";
    [Localizacion addAnnotation:annotationPoint28];
    
    //Pin29
    CLLocationCoordinate2D pin29;
    pin29.latitude = 39.883791;
    pin29.longitude = -4.041659;
    MKPointAnnotation *annotationPoint29 = [[MKPointAnnotation alloc] init];
    annotationPoint29.coordinate = pin29;
    annotationPoint29.title = @"IDC Hospital de Las Tres Culturas";
    annotationPoint29.subtitle = @"Dr. DÌaz Jara y Dr. De AndrÈs";
    [Localizacion addAnnotation:annotationPoint29];
    
    //Pin30
    CLLocationCoordinate2D pin30;
    pin30.latitude = 39.868429;
    pin30.longitude = -4.0416722;
    MKPointAnnotation *annotationPoint30 = [[MKPointAnnotation alloc] init];
    annotationPoint30.coordinate = pin30;
    annotationPoint30.title = @"Solimat Mutua";
    annotationPoint30.subtitle = @"Dr. JosÈ Cid Calzada";
    [Localizacion addAnnotation:annotationPoint30];
    
    //Pin31
    CLLocationCoordinate2D pin31;
    pin31.latitude = 39.8823249;
    pin31.longitude = -4.0305425;
    MKPointAnnotation *annotationPoint31 = [[MKPointAnnotation alloc] init];
    annotationPoint31.coordinate = pin31;
    annotationPoint31.title = @"Centro MÈdico Integral";
    annotationPoint31.subtitle = @"Dr. JosÈ Cid Calzada";
    [Localizacion addAnnotation:annotationPoint31];
    
    //Pin32
    CLLocationCoordinate2D pin32;
    pin32.latitude = 39.4683187;
    pin32.longitude = -0.3769897;
    MKPointAnnotation *annotationPoint32 = [[MKPointAnnotation alloc] init];
    annotationPoint32.coordinate = pin32;
    annotationPoint32.title = @"ClÌnica Santa Clara";
    annotationPoint32.subtitle = @"Dr. Juan Carlos Tornero Tornero";
    [Localizacion addAnnotation:annotationPoint32];
    
    //Pin33
    CLLocationCoordinate2D pin33;
    pin33.latitude = 41.655963;
    pin33.longitude = -4.7291075;
    MKPointAnnotation *annotationPoint33 = [[MKPointAnnotation alloc] init];
    annotationPoint33.coordinate = pin33;
    annotationPoint33.title = @"ClÌnica del Dolor Valladolid";
    annotationPoint33.subtitle = @"Dr. Juan Manuel Vaca Miguel";
    [Localizacion addAnnotation:annotationPoint33];
   
    
    //Pin34
    CLLocationCoordinate2D pin34;
    pin34.latitude = 40.005547;
    pin34.longitude = 3.841367;
    MKPointAnnotation *annotationPoint34 = [[MKPointAnnotation alloc] init];
    annotationPoint34.coordinate = pin34;
    annotationPoint34.title = @"ClÌnica Juaneda Menorca";
    annotationPoint34.subtitle = @"Dr. Manuel Corral Rosado";
    [Localizacion addAnnotation:annotationPoint34];
    
    //Pin35
    CLLocationCoordinate2D pin35;
    pin35.latitude = 41.649656;
    pin35.longitude = -4.720165;
    MKPointAnnotation *annotationPoint35 = [[MKPointAnnotation alloc] init];
    annotationPoint35.coordinate = pin35;
    annotationPoint35.title = @"Sanatorio Sagrado CorazÛn ";
    annotationPoint35.subtitle = @"Dr. Juan Manuel Vaca Miguel";
    [Localizacion addAnnotation:annotationPoint35];
    
    
   
    
    
    
}


- (void)didReceiveMemoryWarning
{
    [super didReceiveMemoryWarning];
    // Dispose of any resources that can be recreated.
}

@end
