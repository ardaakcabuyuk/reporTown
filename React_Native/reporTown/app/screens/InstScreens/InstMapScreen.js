import React, { useState, useEffect, useRef, useLayoutEffect } from 'react';
import { ImageBackground, StyleSheet, View, Dimensions} from "react-native";
import { useFocusEffect } from '@react-navigation/native';
import MapView from 'react-native-maps';
import * as Location from 'expo-location';

function InstMapScreen(props) {
    
    const [location, setLocation] = useState({latitude: 10, longtitude: 10, latitudeDelta: 0.0922, longitudeDelta: 0.0421});
    
    const mapRef = React.createRef();

    const goToMyLocation = async () => {
        mapRef.current.animateCamera({center: {"latitude":location.coords.latitude, "longitude": location.coords.longitude}});
    }

    useEffect(() => {
        (async () => {
            let { status } = await Location.requestForegroundPermissionsAsync();
            if (status !== 'granted') {
                return;
            }

            let location = await Location.getCurrentPositionAsync({
                accuracy: Location.Accuracy.Balanced,
                enableHighAccuracy: true,
                timeInterval: 5
            });
            setLocation({
                longitude: location.coords.longitude,
                latitude: location.coords.latitude,
                longitudeDelta: 0.04,
                latitudeDelta: 0.09
            } 
        );
            goToMyLocation();
        })();
    }, []);
    
    return (
        <View style={styles.container}>
            <MapView ref={mapRef} style={styles.map} showsUserLocation={true} region={location}>
                </MapView></View>
      );
    }

    const styles = StyleSheet.create({
        container:{
            flex: 1,
            alignItems: 'center',
            backgroundColor: '#fff',
            justifyContent: 'center',
        },
        map: {
            width: Dimensions.get('window').width,
            height: Dimensions.get('window').height,
            
        },
    });
export default InstMapScreen;
