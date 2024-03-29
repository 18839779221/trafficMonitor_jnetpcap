package deal;

import java.io.IOException;
import java.util.List;

import org.jnetpcap.Pcap;
import org.jnetpcap.PcapDumper;
import org.jnetpcap.packet.PcapPacket;

public class PacketDumper {

	//单例模式
	private static PacketDumper pdumper = new PacketDumper();
	private static PcapDumper dumper;

	private PacketDumper() {
	}
	
	public static PacketDumper newInstance(String filepath) {
		dumper = CatchPacket.newInstance().getPcap().dumpOpen(filepath);
		if(null==dumper) {
			System.out.println("dumper is null");
			System.out.println("err "+CatchPacket.newInstance().getPcap().getErr());
		}
		return pdumper;
	}
	
	public String dumperPacket(List<PcapPacket> packets) {
		try {
			for (PcapPacket packet : packets) {
				dumper.dump(packet);
			}
			dumper.close();
		} catch (Exception e) {
			return "error "+CatchPacket.newInstance().getPcap().getErr();
		}
		return null;
	}
}
