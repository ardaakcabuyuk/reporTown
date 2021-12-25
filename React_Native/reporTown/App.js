import { StatusBar } from 'expo-status-bar';
import React from 'react';
import { NavigationContainer } from "@react-navigation/native";
import { createStackNavigator } from "@react-navigation/stack";
import { StyleSheet, Text, View } from 'react-native';
import LoginScreen from './app/screens/LoginScreen';
import WelcomeScreen from './app/screens/WelcomeScreen';
import RegisterChooseScreen from './app/screens/RegisterChooseScreen';
import CitizenRegisterScreen from './app/screens/CitizenRegisterScreen';
import InstitutionRegisterScreen from './app/screens/InstitutionRegisterScreen';

const Stack = createStackNavigator();

export default function App() {

  return (
    <NavigationContainer>
      <Stack.Navigator headerMode="false" initialRouteName="LoginScreen">
        <Stack.Screen name="LoginScreen" component={LoginScreen} />
        <Stack.Screen name="WelcomeScreen" component={WelcomeScreen} />
        <Stack.Screen name="RegisterChooseScreen" component={RegisterChooseScreen} />
        <Stack.Screen name="CitizenRegisterScreen" component={CitizenRegisterScreen} />
        <Stack.Screen name="InstitutionRegisterScreen" component={InstitutionRegisterScreen} />
      </Stack.Navigator>
    </NavigationContainer>
  );
}
