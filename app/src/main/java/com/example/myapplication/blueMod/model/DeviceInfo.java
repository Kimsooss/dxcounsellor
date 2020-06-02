/*
 * Copyright (C) 2013 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.example.myapplication.blueMod.model;

import android.bluetooth.BluetoothDevice;
import android.os.Parcel;
import android.os.Parcelable;

public class DeviceInfo implements Parcelable {
    public static final Creator<DeviceInfo> CREATOR = new Creator<DeviceInfo>() {
        public DeviceInfo createFromParcel(Parcel parcel) {
            return new DeviceInfo(parcel);
        }

        public DeviceInfo[] newArray(int i) {
            return new DeviceInfo[i];
        }
    };
    String Color;
    String address;
    BluetoothDevice device;
    String deviceClass;
    String deviceRawData;
    String deviceScanResult;
    String deviceUUID;
    String firstRssi;
    String firstTime;
    String name;
    String rssi;
    String serviceUUID;
    String time;
    String type;
    int heartBeat = 0;

    public int getHeartBeat() {
        return heartBeat;
    }

    public void setHeartBeat(int heartBeat) {
        this.heartBeat = heartBeat;
    }

    public int describeContents() {
        return 0;
    }

    public DeviceInfo() {
    }

    protected DeviceInfo(Parcel parcel) {
        this.device = (BluetoothDevice) parcel.readParcelable(BluetoothDevice.class.getClassLoader());
        this.name = parcel.readString();
        this.address = parcel.readString();
        this.rssi = parcel.readString();
        this.Color = parcel.readString();
        this.type = parcel.readString();
        this.time = parcel.readString();
        this.deviceClass = parcel.readString();
        this.deviceScanResult = parcel.readString();
        this.deviceUUID = parcel.readString();
        this.deviceRawData = parcel.readString();
        this.firstRssi = parcel.readString();
        this.firstTime = parcel.readString();
        this.serviceUUID = parcel.readString();
    }

    public String getFirstRssi() {
        return this.firstRssi;
    }

    public void setFirstRssi(String str) {
        this.firstRssi = str;
    }

    public String getFirstTime() {
        return this.firstTime;
    }

    public void setFirstTime(String str) {
        this.firstTime = str;
    }

    public String getDeviceScanResult() {
        return this.deviceScanResult;
    }

    public void setDeviceScanResult(String str) {
        this.deviceScanResult = str;
    }

    public String getDeviceUUID() {
        return this.deviceUUID;
    }

    public void setDeviceUUID(String str) {
        this.deviceUUID = str;
    }

    public String getDeviceRawData() {
        return this.deviceRawData;
    }

    public void setDeviceRawData(String str) {
        this.deviceRawData = str;
    }

    public BluetoothDevice getDevice() {
        return this.device;
    }

    public void setDevice(BluetoothDevice bluetoothDevice) {
        this.device = bluetoothDevice;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String str) {
        this.name = str;
    }

    public String getAddress() {
        return this.address;
    }

    public void setAddress(String str) {
        this.address = str;
    }

    public String getRssi() {
        return this.rssi;
    }

    public void setRssi(String str) {
        this.rssi = str;
    }

    public String getColor() {
        return this.Color;
    }

    public void setColor(String str) {
        this.Color = str;
    }

    public String getType() {
        return this.type;
    }

    public void setType(String str) {
        this.type = str;
    }

    public String getTime() {
        return this.time;
    }

    public void setTime(String str) {
        this.time = str;
    }

    public String getDeviceClass() {
        return this.deviceClass;
    }

    public void setDeviceClass(String str) {
        this.deviceClass = str;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeParcelable(this.device, i);
        parcel.writeString(this.name);
        parcel.writeString(this.address);
        parcel.writeString(this.rssi);
        parcel.writeString(this.Color);
        parcel.writeString(this.type);
        parcel.writeString(this.time);
        parcel.writeString(this.deviceClass);
        parcel.writeString(this.deviceScanResult);
        parcel.writeString(this.deviceRawData);
        parcel.writeString(this.deviceUUID);
        parcel.writeString(this.firstRssi);
        parcel.writeString(this.firstTime);
        parcel.writeString(this.serviceUUID);
    }

    public String getServiceUUID() {
        return this.serviceUUID;
    }

    public void setServiceUUID(String str) {
        this.serviceUUID = str;
    }
}