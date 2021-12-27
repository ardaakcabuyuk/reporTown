import React, { useState, useEffect, useRef } from "react";
import {
  View,
  Text,
  SafeAreaView,
  StatusBar,
  Dimensions,
  StyleSheet,
  ScrollView,
  Image,
  TextInput,
  TouchableOpacity,
  Linking,
} from "react-native";
import FontAwesome from "react-native-vector-icons/FontAwesome";
const { width, height } = Dimensions.get("window");
import SelectDropdown from "react-native-select-dropdown";
import BouncyCheckbox from "react-native-bouncy-checkbox";

export default InstitutionRegistrationScreen = () => {
  const [countries, setCountries] = useState([]);
  const [cities, setCities] = useState([]);
  const [checkboxState, setCheckboxState] = React.useState(false);

  const citiesDropdownRef = useRef();

  useEffect(() => {
    setTimeout(() => {
      setCountries([
        { title: "Egypt", cities: [{ title: "Cairo" }, { title: "Alex" }] },
        {
          title: "Canada",
          cities: [{ title: "Toronto" }, { title: "Quebec City" }],
        },
        {
          title: "Turkey",
          cities: [
            { title: "Istanbul" },
            { title: "Ankara" },
            { title: "Trabzon" },
            { title: "Antalya" },
            { title: "Mugla" },
            { title: "Adana" },
            { title: "Samsun" },
            { title: "Sinop" },
          ],
        },
      ]);
    }, 1000);
  }, []);

  return (
    <SafeAreaView style={styles.saveAreaViewContainer}>
      <StatusBar backgroundColor="#000" barStyle="light-content" />
      <View style={styles.viewContainer}>
        <ScrollView
          showsVerticalScrollIndicator={false}
          alwaysBounceVertical={false}
          contentContainerStyle={styles.scrollViewContainer}
        >
          <View style={styles.regForm}>
            <View style={{ height: "20%", alignItems: "center" }}>
              <Image
                source={require("../gui_components/reportown.png")}
                style={{
                  width: "60%",
                  height: "100%",
                  resizeMode: "contain",
                  backgroundColor: "#080f26",
                }}
              />
            </View>
            <Text style={styles.header2}>Register</Text>
            <TextInput
              style={styles.textinput}
              placeholder="Institution Name"
              underlineColorAndroid={"transparent"}
              placeholderTextColor={"gray"}
            />

            <View style={styles.dropdownsRow}>
              <SelectDropdown
                data={countries}
                onSelect={(selectedItem, index) => {
                  citiesDropdownRef.current.reset();
                  setCities([]);
                  setCities(selectedItem.cities);
                }}
                defaultButtonText={"Select country"}
                buttonTextAfterSelection={(selectedItem, index) => {
                  return selectedItem.title;
                }}
                rowTextForSelection={(item, index) => {
                  return item.title;
                }}
                buttonStyle={styles.dropdown1BtnStyle}
                buttonTextStyle={styles.dropdown1BtnTxtStyle}
                renderDropdownIcon={(isOpened) => {
                  return (
                    <FontAwesome
                      name={isOpened ? "chevron-up" : "chevron-down"}
                      color={"#444"}
                      size={18}
                    />
                  );
                }}
                dropdownIconPosition={"right"}
                dropdownStyle={styles.dropdown1DropdownStyle}
                rowStyle={styles.dropdown1RowStyle}
                rowTextStyle={styles.dropdown1RowTxtStyle}
              />
              <View style={{ width: 12 }} />
              <SelectDropdown
                ref={citiesDropdownRef}
                data={cities}
                onSelect={(selectedItem, index) => {}}
                defaultButtonText={"Select city"}
                buttonTextAfterSelection={(selectedItem, index) => {
                  return selectedItem.title;
                }}
                rowTextForSelection={(item, index) => {
                  return item.title;
                }}
                buttonStyle={styles.dropdown2BtnStyle}
                buttonTextStyle={styles.dropdown2BtnTxtStyle}
                renderDropdownIcon={(isOpened) => {
                  return (
                    <FontAwesome
                      name={isOpened ? "chevron-up" : "chevron-down"}
                      color={"#444"}
                      size={18}
                    />
                  );
                }}
                dropdownIconPosition={"right"}
                dropdownStyle={styles.dropdown2DropdownStyle}
                rowStyle={styles.dropdown2RowStyle}
                rowTextStyle={styles.dropdown2RowTxtStyle}
              />
            </View>

            <TextInput
              style={styles.textinput}
              placeholder="Username"
              underlineColorAndroid={"transparent"}
              placeholderTextColor={"gray"}
            />

            <TextInput
              style={styles.textinput}
              placeholder="Email"
              underlineColorAndroid={"transparent"}
              placeholderTextColor={"gray"}
            />
            <TextInput
              style={styles.textinput}
              placeholder="Password"
              secureTextEntry={true}
              underlineColorAndroid={"transparent"}
              placeholderTextColor={"gray"}
            />

            <TextInput
              style={styles.textinput}
              placeholder="Password (Again)"
              secureTextEntry={true}
              underlineColorAndroid={"transparent"}
              placeholderTextColor={"gray"}
            />

            <View
              style={{
                width: "90%",
                alignItems: "center",
                paddingTop: "5%",
              }}
            >
              <View
                style={{
                  width: "100%",
                  flexDirection: "row",
                  justifyContent: "center",
                  alignItems: "center",
                }}
              >
                <BouncyCheckbox
                  fillColor="#cb7b23"
                  isChecked={checkboxState}
                  disableBuiltInState
                  style={{ width: "8.5%", aspectRatio: 1 }}
                  onPress={() => setCheckboxState(!checkboxState)}
                />

                <Text
                  style={{ color: "#cb7b23", fontWeight: "bold" }}
                  onPress={() =>
                    Linking.openURL("https://passmiracle.com/kvvk.pdf")
                  }
                >
                  I understand and accept terms of usage.
                </Text>
              </View>
            </View>

            <TouchableOpacity style={styles.button}>
              <Text style={styles.buttontext}>Register</Text>
            </TouchableOpacity>
          </View>
        </ScrollView>
      </View>
    </SafeAreaView>
  );
};

const styles = StyleSheet.create({
  shadow: {
    shadowColor: "#000",
    shadowOffset: { width: 0, height: 6 },
    shadowOpacity: 0.1,
    shadowRadius: 10,
    elevation: 10,
  },
  regForm: {
    backgroundColor: "#080f26",
  },
  header2: {
    fontSize: 24,
    color: "#fff",
    paddingBottom: 10,
    marginBottom: 40,
    alignItems: "center",
    textAlign: "center",
    paddingTop: 30,
  },
  textinput: {
    alignSelf: "stretch",
    height: 40,
    marginLeft: 30,
    marginRight: 30,
    paddingLeft: 10,
    paddingRight: 10,
    marginBottom: 30,
    color: "#fff",
    backgroundColor: "white",
    borderColor: "#f8f8f8",
    borderWidth: 1,
    borderRadius: 5,
  },
  button: {
    alignItems: "center",
    padding: 20,
    backgroundColor: "#cb7b23",
    marginBottom: 250,
    width: 200,
    borderRadius: 5,
    marginLeft: "25%",
  },
  buttontext: {
    color: "#fff",
    fontWeight: "bold",
  },

  header: {
    flexDirection: "row",
    width,
    height: 50,
    alignItems: "center",
    justifyContent: "center",
    backgroundColor: "#F6F6F6",
  },
  headerTitle: { color: "#000", fontWeight: "bold", fontSize: 16 },
  saveAreaViewContainer: { flex: 1, backgroundColor: "#080f26" },
  viewContainer: { flex: 1, width, backgroundColor: "#080f26" },
  scrollViewContainer: {
    flexGrow: 1,
    justifyContent: "space-between",
    alignItems: "center",
    paddingVertical: "10%",
  },
  dropdownsRow: {
    flexDirection: "row",
    width: "100%",
    paddingHorizontal: "5%",
    marginBottom: 25,
  },

  dropdown1BtnStyle: {
    flex: 1,
    height: 50,
    backgroundColor: "#FFF",
    borderRadius: 8,
    borderWidth: 1,
    borderColor: "#444",
  },
  dropdown1BtnTxtStyle: { color: "#444", textAlign: "left" },
  dropdown1DropdownStyle: { backgroundColor: "#EFEFEF" },
  dropdown1RowStyle: {
    backgroundColor: "#EFEFEF",
    borderBottomColor: "#C5C5C5",
  },
  dropdown1RowTxtStyle: { color: "#444", textAlign: "left" },

  dropdown2BtnStyle: {
    flex: 1,
    height: 50,
    backgroundColor: "#FFF",
    borderRadius: 8,
    borderWidth: 1,
    borderColor: "#444",
  },
  dropdown2BtnTxtStyle: { color: "#444", textAlign: "left" },
  dropdown2DropdownStyle: { backgroundColor: "#EFEFEF" },
  dropdown2RowStyle: {
    backgroundColor: "#EFEFEF",
    borderBottomColor: "#C5C5C5",
  },
  dropdown2RowTxtStyle: { color: "#444", textAlign: "left" },
});
